# kh_academy
How to run:

Just clone this repository to local, and run the whole project.

The default test cases is 5 students per coach, 15 people per disconnected group and 30 groups in total.
This is provided in the Relation.java file.


Design ideas:

I designed three classes. 

One is the user class which includes basic setter and getter methods and version variable.

The relation class decribes the max user in a group, the disconnected group number and the student number per coach. I do it recursively
to find the relationship of each user.

The infection algortithm is to find random user at first, the use recursive method to find all the users connected with it. Then update 
their version number.

The limited infection algorithm is to find a specific group which size is the smallest, then infect all the users in this group. Then 
find the second smallest group to infect, thus closing to the target number.

If target number is less than a group size, then we add the user one by one.

This makes sense because we should always add from the smallest. 
