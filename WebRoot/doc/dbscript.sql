create database if not exists `noword`;
use `noword`;

CREATE TABLE  if not exists `t_messageboard` (             
  `id` int(11) NOT NULL auto_increment,     
  `guestname` varchar(50) default NULL,     
  `title` varchar(50) default NULL,         
  `qq` varchar(15) default NULL,            
  `email` varchar(50) default NULL,         
  `homepageUrl` varchar(100) default NULL,  
  `sex` varchar(1) default NULL,            
  `pic` varchar(50) default NULL,           
  `leaveword` varchar(999) default NULL,    
  `createtime` datetime default NULL,       
  `ip` varchar(30) default NULL,            
  PRIMARY KEY  (`id`)                       
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

CREATE TABLE  if not exists `t_user` (                  
  `id` int(11) NOT NULL auto_increment,  
  `username` varchar(50) default NULL,   
  `password` varchar(50) default NULL,   
  `userType` int(1) default NULL,        
  PRIMARY KEY  (`id`)                    
) ENGINE=InnoDB DEFAULT CHARSET=gbk;