# Student CLub Manager
Student Club Manager is class project for Bilkent CS319. The main objective of the project is to enable managing clubs, club events and announcements. 

# Languages and frameworks(tentative)  
[C#](https://docs.microsoft.com/en-us/dotnet/csharp/), [ASP.NET](https://dotnet.microsoft.com/apps/aspnet), [SQL](?), [jQuery](https://jquery.com/), [Bootstrap](https://getbootstrap.com/)

# Main Features  
The main theme of our project is to allow student club managers of Bilkent University to manage the club members and club events and students to access functionaloties regarding clubs. There will be two different accounts, one for students another for club managers 
  ## For student:
  - See active club events (details: club hosting the event, date, time, location, GE points(if any))
  - See list of avtive clubs 
  - See active clubs(each club will have a seperate, simple profile page)
  - Enroll in activities 
  - See activities of a certain club
  - Apply for membership for clubs( The students might have to fill in a form created by the club director)
  - Show enrolled activities(name, date, Club, location)
  - Show enrolled clubs and status decided by the club manager 
  - Receive announcement about club activities through bilkent email

  ## For Club managers 
  - Promotion button to promote members of the club
  - Create new events
  - See participants of the club and their roles(participant, active member, manager, etc )
  - Review applications from students that applied to the club
  - Have certain permissions 
  - Permissions:
  	- Create new member
    - Kick out a member
	  - Create(edit) an event
    - (optional) take attendants
  - General director can manage permissions of all the members of the club
  - If the permission allows so create a new member with certain permissions

In order to use this project as an extension to Google Chrome:
  - Download/clone the project from this repository
  - Install the following dependicies in the repository
           [Cheerio npm](https://www.npmjs.com/package/cheerio) and
           [node-fetch npm](https://www.npmjs.com/package/node-fetch)
  - Navigate to ```chrome://extensions``` or **Chrome menu > More Tools > Extensions** or **Extensions menu button > Manage Extensions**
  - Make sure ```Developer mode``` is enabled (toggle switch on the top right) 
  - Click ```Load unpacked``` button and choose ```willow/demo/``` as the extension directory

After these steps, the extension should be loaded and be ready for use. To see any implememented changes during the develepment process, click the reload button.

<p align="center">
<img width="600" alt="Screen Shot 2021-08-05 at 10 46 15" src="https://user-images.githubusercontent.com/63513562/128313019-f1525839-8c3a-4e1e-bce0-83417e895b16.png">
</p>

# Additional features
Once the extension is loaded, the user can browse through the Internet as they please and whevener they want to see the current graph of their browsing, they can click the extension icon on the top right corner. This will present the browse history as an interactive graph, where the nodes correspond to the web pages and the edges represent hyperlinks followed from one page to another. The currently viewable graph belongs to the current browse session. At any time the user has the option to start a new session (empty the graph and start from scratch). They may also export the current session at any time to import back at a later time (or exchange with a friend).

<p align="center">
<img width="600" alt="Screen Shot 2021-08-05 at 10 33 19" src="https://user-images.githubusercontent.com/63513562/128310730-72ff752e-fe81-4ed5-a32e-d2cbb85311c3.png">
</p>

When the extension icon is clicked, by default the Willow extension window will be opened in a new dedicated tab. The user may also choose to view Willow on a side panel. This side panel is movable and resizable. 

<p align="center">
<img width="800" alt="Screen Shot 2021-08-05 at 16 51 49" src="https://user-images.githubusercontent.com/63513562/128361709-ba42f967-e627-447e-be9a-d08d6162f8ce.png">
</p>

The user can revisit a desired web page by double-clicking on the associated node (or restore any previous session and visit the desired webpage from that session). 

## Discover web site map
Willow also provides a tool to crawl a particular URL and find all webpages underneath the given page. Willow also displays the number of broken links on a particular page, if any. This feature should be especially useful for web site developers as they can clearly see the navigation patterns from the home page to others as well as any broken links.

<p align="center">
<img width="600" alt="Screen Shot 2021-08-05 at 11 42 41" src="https://user-images.githubusercontent.com/63513562/128320391-5a54cb2a-426e-4601-a5df-73f8cb329274.png">
</p>

## Keyword search
The user may also search a keyword using the Search tool availble in the toolbar. The nodes whose URL or title contain the keyword will be highlighted as a result.

<p align="center">
<img width="600" alt="Screen Shot 2021-08-06 at 13 12 40" src="https://user-images.githubusercontent.com/63513562/128495405-72105481-7ccf-460f-b1c8-7c0e772a0b0e.png">
</p>

## Node annotations
- The *green arrow* on the upper left of a node indicates the current active page in a tab. So, if multiple tabs are opened after the session started, there might be multiple such pages (one per tab).

- The *note* icon on the lower right of a node indicates that a note has been added to the web page associated with this node.

<p align="center">
<img width="200" alt="Screen Shot 2021-08-05 at 13 34 42" src="https://user-images.githubusercontent.com/63513562/128336494-9b0c6e12-91d2-41b4-85fa-c4bc7b8a4185.png">
</p>

The users are also allowed to change each node's border color, size, etc. for classification purposes.

# Team 
[Ahmet Hakan Yilmaz](https://github.com/AhmetHakanYilmaz), [Akin Kutlu](https://github.com/AkinKutlu00), [Ebrar Bozkurt](https://github.com/EbrarBozkurt), [Fatma Sena Genc](https://github.com/fatmasenagenc),[Selbi Ereshova](https://github.com/SelbiEreshova), [Zehra Erdem](https://github.com/ZehraErdem) of [Computer Engineering Department](http://www.cs.bilkent.edu.tr) at Bilkent University.

