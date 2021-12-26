package luna.clubverse.backend.user.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.event.entity.Event;
import luna.clubverse.backend.user.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("student")
public class Student extends User{

    private int bilkentId;

    @ManyToMany(mappedBy = "appliedStudents")
    private Set<Club> waitingApprovalClubs;

    @ManyToMany(mappedBy = "members")
    private Set<Club> registeredClubs;

    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Event> enrolledEvents;

    @ManyToMany(mappedBy = "attendedStudents")
    private Set<Event> attendedEvents;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "student")
    private Set<Title> titles;

    public Student(Long id, String username, String password, String name,String surname, byte[] profilePhoto, @Email String mail, Set<Authority> authorities, int bilkentId) {
        super(id, username, password, name, surname,profilePhoto, UserType.STUDENT, mail, authorities);
        this.bilkentId = bilkentId;
        registeredClubs = new HashSet<Club>();
        waitingApprovalClubs = new HashSet<Club>();
        enrolledEvents = new HashSet<Event>();
        attendedEvents = new HashSet<Event>();
        titles = new HashSet<Title>();
    }


    public Student() {

    }

    public List<Integer> getPermissons(Long clubId) {

        List<Integer> result = new ArrayList<Integer>();

        return result;
    }

    public List<Authority> updateAuthority(Long clubId, List<Boolean> memberPermissions) {

        Authority eventManagement = new Authority("EVENT_MANAGEMENT", clubId);
        List<Authority> authorities = new ArrayList<Authority>();

        if(!getAuthorities().contains(eventManagement) && memberPermissions.get(0)){
            addAuthority(eventManagement);
            authorities.add(eventManagement);
        } else if( getAuthorities().contains(eventManagement) && !memberPermissions.get(0)) {
            removeAuthority(eventManagement);
        }

        Authority financeManagement = new Authority("FINANCE_MANAGEMENT", clubId);

        if(!getAuthorities().contains(financeManagement) && memberPermissions.get(1)){
            addAuthority(financeManagement);
            authorities.add(financeManagement);
        } else if( getAuthorities().contains(financeManagement) && !memberPermissions.get(1)) {
            removeAuthority(financeManagement);
        }

        Authority membershipManagement = new Authority("MEMBERSHIP_MANAGEMENT", clubId);

        if(!getAuthorities().contains(membershipManagement) && memberPermissions.get(2)){
            addAuthority(membershipManagement);
            authorities.add(membershipManagement);
        } else if( getAuthorities().contains(membershipManagement) && !memberPermissions.get(2)) {
            removeAuthority(membershipManagement);
        }

        return authorities;
    }

    public Title updateTitle(Long clubId, String title) {

        Title titleObject = titleFoundByClub(clubId);

        if(titleObject == null)
            titleObject = new Title(clubId,title);
        else
            titleObject.setTitle(title);

        titleObject.setStudent(this);

        return titleObject;

    }

    public Title titleFoundByClub(Long clubId) {

        for(Title title: titles) {
            if (title.getClubId().equals(clubId))
                return title;
        }

        return  null;
    }

    public void deleteTitles(Long clubId){

        Title title = titleFoundByClub(clubId);

        if (title!=null){
            titles.remove(title);
        }

    }

    public void deletePermissions(Long clubId){
        setAuthorities(new HashSet<>(getOnlyAuthorities().stream().filter(it -> !Objects.equals(it.getClubId(), clubId)).toList()));
    }
}
