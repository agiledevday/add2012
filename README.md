[![Build Status](https://buildhive.cloudbees.com/job/agiledevday/job/add2012/badge/icon)](https://buildhive.cloudbees.com/job/agiledevday/job/add2012/)

Has Learnt It application for add2012
=======

Building and Running
------------
Building application and run test:  
~~~ sh
mvn clean install
~~~

Run application in embedded server:   
~~~ sh
mvn jetty:run
~~~
  

Continous deployment
--------
Application is deployed from master branch to heroku platform:    
http://add2012.herokuapp.com/

Ci server used for deployment is:  
http://ci.pragmatists.pl:9090/  

Wiki
--------
[Troubleshooting page](https://github.com/agiledevday/add2012/wiki/Troubleshooting)

[Git for add2012](https://github.com/agiledevday/add2012/wiki/just-enough-git-for-add2012)