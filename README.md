# ElectionManagementSystem-in-java-and-oracle-sql

Abstract:
        Internet voting system can be a viable alternative for conducting an election and such a voting system must provide the same level of security as ordinary paper based elections. This paper deals with designing, building and testing a Pakistani internet voting system based on open list election where voters have some influence on the order in which a party's candidates are elected. This online voting system is highly secured, and its design is very simple, ease of use and also reliable. It also creates and manages voting and election details as all the voters must login by inserting their verified information and password, then select their favorable candidates to vote for. This will lead to increasing the voting percentage in Pakistan. The system is designed using programming languages such as (JAVA and SQLite).
Introduction
Pakistanis are becoming part of growing digital Pakistan. In Pakistan Election System involve manual vote casting using paper based ballots. Also counting are done through hands as well as registration of Voters and Candidates are manual. The Proposed system involve registration of Voters and Candidates through computers. Also contain computerize casting and counting of votes. The number of votes candidate obtained. It restricts duplication of votes. It declares the winner at each constituency. Voter Verification in digital Voter list. The project is divided in two section Administration and User. The Administrator responsibility is on ECP to maintain the overall aspects of the system Administration involve Voters registration, Candidate registration, Result declaration etc. The user is allowing to see the list of all candidates relevant to its constituency and should cast vote to their desired candidates
Internet voting is intended as a service to the electorate, so that the voters might have more convenience to cast their vote. They can vote from anywhere in the world by any computer connected to the Internet. The implementation of this internet voting system requires various technical solutions to ensure accurate voter authentication, secrecy of the ballot and security. Proposed System.
Purposed System:
In our proposed system the list of candidates who are nominating will be available in the online. The voter’s wants to fill a registration form and the administrator will give rights for voting process. Once the permission is granted the user can login using their own username and password. After enter their username and password verification can vote for favorite candidate. Once the voting process is completed the report will be generated at the same time. The candidate with the maximum vote is regarded as a Winner among the user. It saves time, avoid error in counting and there will be no invalid votes. It makes the voting process easy and it will be effective manner.
Advantages/Benefits of Proposed System:
1.	Electronic Voting providing ease to voter.
2.	The proposed system overcome the decades of existing system
3.	Corruption is handled through E-Voting.
4.	Counting of votes is handled in split of seconds.
5.	Election Results is announced at time.
6.	Human resources is reduced very much.
7.	Election Conduct at time.
Scope:
A.	Authorization and authentication:
Authorization is like only eligible or legal person can vote. In elections Iraqi government requires a minimum age of 18 years old to cast their vote. It can be done by the trusted authority and this process can be done before the elections. Authentication is the process where the validation of the vote is checked at the time of casting the vote.

B.	Mobility:
This is one of the important factors in internet voting system; voter should be able to cast his vote from anywhere in the world, as long they have the required resources with them like internet, PC, etc. For this process authorization, authentication and some security features need to be implemented. 
C.	Flexibility:
 Voters should be able to use different types of devices like desktop, laptop, mobile phones and different networks like Ethernet, wireless and dial-up connections.
D.	Count-ability:
 This process is again dependent on authentication and authorization. If these processes are implemented then count- ability accepts, and this is nothing but to see that only the valid votes are counted.
E.	Anonymity:
 There should be no link between a particular vote and the person who cast the vote. In mandatory voting systems, the fact that the voter has cast a vote should also be recorded.
Modules:
Election Management System (EMS) software - is comprised of several large modules 
1.	Users
2.	Constituency
3.	Voters
4.	Political Party
5.	National Elections
6.	National Candidates
7.	National Voting
8.	National Election Result
9.	Provincial Elections
10.	Provincial Candidates
11.	Provincial Voting
12.	Provincial Election Results

Module 1: Users
In this module users will login/register their self. Types of Users
1.	Admin
2.	Voters
Admin have all the privileges and manage all modules (which is discussed below) while Voters can only Vote and View Election Results. Admin will Login through a user name and password and creates voters which will login through CNIC and associated password assigned by Admin (Authority)
Module 2:  Constituency
Admin (Authority) can manage Constituency. Admin can manage in three ways
1.	View All Constituencies
2.	Add New Constituency (Constituency id and name)
3.	Edit/Update Constituency
4.	Delete Constituency

Module 3: Voters
In this module the admin will manage the voters. Admin can manage in three ways
1.	View All Voters 
2.	Add New Voters (voters id, Cnic and passwords)
3.	Edit/Update/Modify the Voters
4.	Delete Voters
Voters can then vote and only view the results of respective Elections.
Module 4: Political Party
In this module the admin will manage the Political Parties. Admin can manage in three ways
1.	View All Political Party
2.	Add New Political Party (Party id, name )
3.	Edit/Update/Modify the Political Party
4.	Delete Political Party

Module 5: National Elections
In this module the admin will manage the National Elections. Admin can manage in three ways
1.	View All National Elections
2.	Add National Elections (Election id and name)
3.	Edit/Update/Modify the National Elections
4.	Delete National Elections

Module 6: National Candidates
In this module the admin will manage the National Candidates. Admin can manage in three ways
1.	View All National Candidates
2.	Add National Candidates (Candidate’s id, name, political party and constituency)
3.	Edit/Update/Modify the National Candidates
4.	Delete National Candidates

Module 7: National Voting
In this module the Voters will Vote for their Candidate in their respective constituency and Elections. Admin can manage in three ways
1.	View All National Voting
2.	Publish National Voting

Module 8: National Election Result
In this module the admin will manage the National Election Result. Admin can manage in three ways
1.	View All National Election Result
2.	Publish National Election Result (Candidate’s id, name, political party, constituency and number of Votes)
3.	Hide National Election Result

Module 9: Provincial Elections
In this module the admin will manage the Provincial Elections. Admin can manage in three ways
5.	View All Provincial Elections
6.	Add Provincial Elections (Election id and name)
7.	Edit/Update/Modify the Provincial Elections
8.	Delete Provincial Elections

Module 10: Provincial Candidates
In this module the admin will manage the Provincial l Candidates. Admin can manage in three ways
3.	View All Provincial Candidates
4.	Add Provincial Candidates (Candidate’s id, name, political party and constituency)
5.	Edit/Update/Modify the Provincial Candidates
6.	Delete Provincial Candidates

Module 11: Provincial Voting
In this module the Voters will Vote for their Candidate in their respective constituency and Elections. Admin can manage in three ways
1.	View All Provincial Voting
2.	Publish Provincial Voting

Module 12: Provincial Election Result
In this module the admin will manage the Provincial Election Result. Admin can manage in three ways
4.	View All Provincial Election Result
5.	Publish Provincial Election Result (Candidate’s id, name, political party, constituency and number of Votes)
6.	Hide Provincial Election Result

System Limitations/Constraints
There are also several Limitation and constraints relating to each remote voting option. This shows that there is no ‘golden solution’ to facilitating access to the ballot and that each option has its own advantages and shortcomings. The main risks related to all remote voting solutions include: 
•	They may require an additional application or registration. 
•	Observing remote voting solutions may be more complex/difficult to organize than person voting. 
•	There may be information asymmetry between voters who vote in advance and those who vote on Election Day. 
