package luna.clubverse.backend.user.entity;

import lombok.Getter;
import lombok.Setter;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.financetable.entity.FinanceTable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Title extends BaseEntity {

    private Long clubId;
    private String title;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Title(Long clubId, String title) {
        this.clubId = clubId;
        this.title = title;
    }

    public Title() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, clubId);
    }

    @Override
    public String toString() {
        return ("Title: " + title + " ClubID: " + clubId);
    }
}
