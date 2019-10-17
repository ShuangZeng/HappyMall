INSERT INTO happymall.role (id, role) VALUES (1,'End User'),(2,'Vendor'),(3,'Customer'),(4,'Admin');

INSERT INTO happymall.user (id, active_ind, create_date, email, full_name, modified_date, `password`, phone, user_name, `enable`,`role_id`) 
VALUES (1000,'A','2019-10-12 00:00:00','fhirsch@aol.com','Gauge Tran','2019-10-11 00:00:00','12345678','(698) 891-5217','gauge_tran', 1,1),
		(1001,'A','2019-10-12 00:00:00','sonnen@msn.com','Brooks Griffin','2019-10-12 00:00:00','12345678','(389) 255-2383','brooks_griffin', 1,2),
		(1002,'A','2019-10-12 00:00:00','mlewan@aol.com','Camren Richmond','2019-10-09 00:00:00','12345678','(989) 993-7549','camren_richmond', 1,1),
		(1003,'A','2019-10-12 00:00:00','cameron@icloud.com','Ciara Cooke','2019-10-09 00:00:00','12345678','(741) 971-2504','ciara_cooke', 1,1),
		(1004,'A','2019-10-12 00:00:00','miltchev@me.com','Aleena Mclean',NULL,'12345678','(550) 824-9338','aleena_mclean', 1,2),
		(1005,'A','2019-10-12 00:00:00','sonnen@hotmail.com','Mareli Rich','2019-10-09 00:00:00','12345678','(801) 421-2668','mareli_rich', 1,2),
		(1006,'A','2019-10-12 00:00:00','phizntrg@yahoo.ca','Jennifer Kramer','2019-10-09 00:00:00','12345678','(951) 202-4374','jennifer_kramer', 1,2),
		(1007,'A','2019-10-12 00:00:00','gastown@live.com','Aliya Clements','2019-10-11 00:00:00','12345678','(900) 609-4718','aliya_clements', 1,2),
		(1008,'A','2019-10-12 00:00:00','wagnerch@optonline.net','Rubi Chavez','2019-10-12 00:00:00','12345678','(755) 894-7319','rubi_chavez', 1,3),
		(1009,'A','2019-10-12 00:00:00','fviegas@comcast.net','Waylon Morse','2019-10-11 00:00:00','12345678','(763) 973-5980','waylon_morse', 1,2),
		(1010,'D','2019-10-12 00:00:00','elmer@aol.com','Liliana Prince',NULL,'12345678','(958) 388-7951','liliana_prince', 1,4),
		(1011,'D','2019-10-12 00:00:00','maradine@verizon.net','Armani Foley',NULL,'12345678','(234) 877-0838','armani_foley', 1,2),
		(1012,'D','2019-10-12 00:00:00','vlefevre@yahoo.com','Giuliana Frazier','2019-10-09 00:00:00','12345678','(911) 910-2046','giuliana_frazier', 1,4),
		(1013,'U','2019-10-12 00:00:00','calin@comcast.net','Jovanni Matthews','2019-10-05 00:00:00','12345678','(770) 736-4855','jovanni_matthews', 1,2),
		(1014,'U','2019-10-12 00:00:00','pjacklam@hotmail.com','Parker Chan','2019-10-09 00:00:00','12345678','(862) 789-2475','parker_chan', 1,2),
		(1015,'A','2019-10-12 00:00:00','raides@outlook.com','Rubi Wright','2019-10-09 00:00:00','12345678','(261) 329-2763','rubi_wright', 1,2),
		(1016,'A','2019-10-12 00:00:00','jsbach@aol.com','Ayla Horne','2019-10-08 00:00:00','12345678','(618) 915-7240','ayla_horne', 1,2),
		(1017,'A','2019-10-12 00:00:00','jyoliver@outlook.com','Lukas Morgan','2019-10-11 00:00:00','12345678','(433) 928-4806','lukas_morgan', 1,2),
		(1018,'A','2019-10-12 00:00:00','dialworld@verizon.net','Terrance Carson','2019-10-12 00:00:00','12345678','(584) 436-9202','terrance_carson', 1,2),
		(1019,'A','2019-10-12 00:00:00','research@verizon.net','Marcelo Owen','2019-10-12 00:00:00','12345678','(450) 543-1324','marcelo_owen', 1,2);

INSERT INTO happymall.user_authorization (id, active_ind, create_date, modified_date, `password`, user_name, user_id)
VALUES (6000,'A','2019-10-12 00:00:00','2019-10-11 00:00:00','12345678','Gauge Tran',1000),
		(6001,'A','2019-10-12 00:00:00','2019-10-12 00:00:00','63254871','Brooks Griffin',1001),
		(6002,'A','2019-10-12 00:00:00','2019-10-09 00:00:00','32798542','Camren Richmond',1002),
		(6003,'A','2019-10-12 00:00:00','2019-10-09 00:00:00','85423556','Ciara Cooke',1003),
		(6004,'A','2019-10-12 00:00:00',NULL,'87963410','Aleena Mclean',1004),
		(6005,'A','2019-10-12 00:00:00','2019-10-09 00:00:00','30124569','Mareli Rich',1005),
		(6006,'A','2019-10-12 00:00:00','2019-10-09 00:00:00','54879630','Jennifer Kramer',1006),
		(6007,'A','2019-10-12 00:00:00','2019-10-11 00:00:00','54213708','Aliya Clements',1007),
		(6008,'A','2019-10-12 00:00:00','2019-10-12 00:00:00','48523791','Rubi Chavez',1008),
		(6009,'A','2019-10-12 00:00:00','2019-10-11 00:00:00','39700241','Waylon Morse',1009),
		(6010,'D','2019-10-12 00:00:00',NULL,'51486027','Liliana Prince',1010),
		(6011,'D','2019-10-12 00:00:00',NULL,'20149374','Armani Foley',1011),
		(6012,'D','2019-10-12 00:00:00','2019-10-09 00:00:00','20317804','Giuliana Frazier',1012),
		(6013,'U','2019-10-12 00:00:00','2019-10-05 00:00:00','34698037','Jovanni Matthews',1013),
		(6014,'U','2019-10-12 00:00:00','2019-10-09 00:00:00','96780037','Parker Chan',1014),
		(6015,'A','2019-10-12 00:00:00','2019-10-09 00:00:00','15637901','Rubi Wright',1015),
		(6016,'A','2019-10-12 00:00:00','2019-10-08 00:00:00','20378964','Ayla Horne',1016),
		(6017,'A','2019-10-12 00:00:00','2019-10-11 00:00:00','20078134','Lukas Morgan',1017),
		(6018,'A','2019-10-12 00:00:00','2019-10-12 00:00:00','96320748','Terrance Carson',1018),
		(6019,'A','2019-10-12 00:00:00','2019-10-12 00:00:00','85341027','Marcelo Owen',1019);

INSERT INTO happymall.product 
VALUES (4000,'2019-10-11 00:00:00','Includes 20 pieces: cot, high chair, slide, and many accessories','2019-10-12 00:00:00','Calico Critters Baby Nursery Set',12.21,15,'In stock',1004),
		(4001,'2019-10-12 00:00:00','Simple setup with an included High Speed HDMI Cable',NULL,'Roku Express | Easy High Definition (HD)',26.99,0,'Out of Stock',1005),
		(4002,'2019-10-09 00:00:00','50% Cotton / 50% Polyester','2019-10-11 00:00:00','Hanes Men\'s Ecosmart Fleece Sweatshirt',16.99,41,'In stock',1006),
		(4003,'2019-10-12 00:00:00','This Barbie doll is dressed to dance the day away in a stage-ready ballerina costume',NULL,'Barbie Fairytale Ballerina Doll, Brunette',6.05,23,'In stock',1007),
		(4004,'2019-09-19 00:00:00','Series Ultra HD Smart TV with HDR and Alexa Compatibility (2019 Model)','2019-10-12 00:00:00','Samsung UN55RU7100FXZA Flat 55-Inch 4K UHD 7',988,10,'In stock',1008),
		(4005,'2019-08-05 00:00:00','Combination DVD Player and Hi-Fi VCR',NULL,'Sony SLVD360P DVD / VCR Combo',105.28,3,'In stock',1014),
		(4006,'2019-10-06 00:00:00','DVD/VCR combo unit with progressive-scan DVD output and 1-month, 8-event VCR programming',NULL,'Sony SLV-D300P Progressive-Scan DVD-VCR Combo',150,35,'In stock',1015),
		(4007,'2019-08-07 00:00:00','All together Now: shows, movies, live TV, YouTube, and photos;',NULL,'Google Chromecast Ultra',69,21,'In stock',1016),
		(4008,'2019-09-30 00:00:00','Full size black RockJam electric guitar with spare strings, guitar picks, and a strap',NULL,'RockJam 6 ST Style Electric Guitar Super Pack with Amp',89.99,0,'Out of Stock',1018),
		(4009,'2019-09-15 00:00:00','Tangle-Free 3.5mm Jack Wired Cord On-Ear Headset for Children/Teens/Boys/Girls','2019-10-12 00:00:00','Kids Headphones - noot products K11 Foldable Stereo',18.99,2,'In stock',1001),
		(4010,'2018-10-20 00:00:00','30 millimeter drivers for rich, full frequency response',NULL,'Sony MDRZX110/BLK ZX Series Stereo Headphones (Black)',15.99,4,'In stock',1019),
		(4011,'2019-07-06 00:00:00','Apple iPad Pro (10.5-inch, Wi-Fi + Cellular, 64GB) - Rose Gold (Previous Model)',NULL,'Apple iPad Pro',579,5,'In stock',1017),
		(4012,'2018-08-21 00:00:00','Echo Dot (3rd Gen) - Smart speaker with Alexa - Charcoal','2019-10-12 00:00:00','Smart speaker with Alexa',49.99,6,'In stock',1010),
		(4013,'2018-06-28 00:00:00','Fire HD 8 Tablet (8\" HD Display, 16 GB) - Black',NULL,'Fire HD 8 Tablet',79.9,0,'Out of Stock',1012);

INSERT INTO happymall.resource 
VALUES (5000,'2019-10-11 00:00:00',NULL,'2019-10-12 00:00:00',4000),
		(5001,'2019-10-12 00:00:00',NULL,'2019-10-12 00:00:00',4001),
		(5002,'2019-10-09 00:00:00',NULL,'2019-10-12 00:00:00',4002),
		(5003,'2019-10-12 00:00:00',NULL,'2019-10-11 00:00:00',4003),
		(5004,'2019-09-19 00:00:00',NULL,'2019-10-12 00:00:00',4004),
		(5005,'2019-08-05 00:00:00',NULL,'2019-10-12 00:00:00',4005),
		(5006,'2019-10-06 00:00:00',NULL,'2019-10-12 00:00:00',4006),
		(5007,'2019-08-07 00:00:00',NULL,NULL,4007),
		(5008,'2019-09-30 00:00:00',NULL,NULL,4008),
		(5009,'2019-09-15 00:00:00',NULL,NULL,4009),
		(5010,'2018-10-20 00:00:00',NULL,NULL,4010),
		(5011,'2019-07-06 00:00:00',NULL,NULL,4011),
		(5012,'2018-08-21 00:00:00',NULL,NULL,4012),
		(5013,'2018-06-28 00:00:00',NULL,NULL,4013);

INSERT INTO happymall.address 
VALUES (2000,'Abington','2019-10-12 00:00:00',1,'777 Brockton Avenue',NULL,'2019-10-12 00:00:00','MA','2169',1000),
		(2001,'Avon','2019-10-12 00:00:00',1,'30 Memorial Drive',NULL,NULL,'MA','2322',1001),
		(2002,'Amsterdam','2019-10-12 00:00:00',1,'101 Sanford Farm Shpg Center',NULL,NULL,'NY','12010',1002),
		(2003,'Batavia','2019-10-12 00:00:00',1,'4133 Veterans Memorial Drive',NULL,NULL,'NY','14020',1003),
		(2004,'Catskill','2019-10-12 00:00:00',1,'30 Catskill',NULL,NULL,'NY','12414',1004),
		(2005,'Foley','2019-10-12 00:00:00',1,'2200 South Mckenzie St',NULL,NULL,'AL','36535',1005),
		(2006,'Gadsden','2019-10-12 00:00:00',1,'340 East Meighan Blvd',NULL,NULL,'AL','35903',1006),
		(2007,'Vestavia Hills','2019-10-12 00:00:00',1,'1300 Montgomery Highway',NULL,NULL,'AL','35216',1007),
		(2008,'Winfield','2019-10-12 00:00:00',1,'2575 Us Hwy 43',NULL,'2019-10-12 00:00:00','AL','35594',1008),
		(2009,'Saraland','2019-10-12 00:00:00',1,'1095 Industrial Pkwy',NULL,NULL,'AL','36571',1009),
		(2010,'Lisbon','2019-10-12 00:00:00',1,'180 River Rd',NULL,NULL,'CT','6351',1010),
		(2011,'Manchester','2019-10-12 00:00:00',1,'420 Buckland Hills Dr',NULL,'2019-10-12 00:00:00','CT','6040',1011),
		(2012,'Rocky Hill','2019-10-12 00:00:00',1,'80 Town Line Rd',NULL,NULL,'CT','6067',1012),
		(2013,'Waterbury','2019-10-12 00:00:00',1,'910 Wolcott St',NULL,'2019-10-12 00:00:00','CT','6705',1013),
		(2014,'East Windsor','2019-10-12 00:00:00',1,'69 Prospect Hill Road',NULL,NULL,'CT','6088',1014),
		(2015,'Airmont','2019-10-12 00:00:00',1,'250 Rt 59',NULL,NULL,'NY','10901',1015),
		(2016,'Batavia','2019-10-12 00:00:00',1,'4133 Veterans Memorial Drive',NULL,NULL,'NY','14020',1016),
		(2017,'Walpole','2019-10-12 00:00:00',1,'550 Providence Hwy',NULL,NULL,'MA','2081',1017),
		(2018,'Northhampton','2019-10-12 00:00:00',1,'180 North King Street',NULL,NULL,'MA','1060',1018),
		(2019,'North Adams','2019-10-12 00:00:00',1,'830 Curran Memorial Hwy',NULL,NULL,'MA','1247',1019),
		 (2020,'Fairfield','2019-10-12 00:00:00',0,'2000 N Court',NULL,'2019-10-12 00:00:00','IA','52556',1004);

INSERT INTO happymall.card_detail 
VALUES (3000,'A','4532904476883190','2019-10-11 00:00:00','2025-06-15 00:00:00','2019-10-10 00:00:00','2019-10-12 00:00:00','Gauge Tran',1000,'VISA',500,2000,1000),
		(3001,'A','4907313621537770','2019-10-10 00:00:00','2019-12-12 00:00:00','2019-10-10 00:00:00','2019-10-12 00:00:00','Brooks Griffin',5000,'VISA',1500,2001,1001),
		(3002,'A','4692200350707520','2019-10-10 00:00:00','2021-12-07 00:00:00','2019-10-04 00:00:00','2019-10-12 00:00:00','Camren Richmond',2500,'VISA',160.5,2002,1002),
		(3003,'A','4692200350707520','2019-10-09 00:00:00','2021-07-04 00:00:00','2018-09-19 00:00:00','2019-10-12 00:00:00','Ciara Cooke',900,'VISA',1239.06,2003,1003),
		(3004,'A','4692200350707520','2019-10-10 00:00:00','2022-10-20 00:00:00','2017-06-08 00:00:00','2019-10-12 00:00:00','Aleena Mclean',1200,'VISA',85.49,2004,1004),
		(3005,'A','4454902576470230','2019-10-10 00:00:00','2023-11-15 00:00:00','2018-08-08 00:00:00','2019-10-11 00:00:00','Mareli Rich',1400,'VISA',106.97,2005,1005);

INSERT INTO happymall.orders (id, create_date, modified_date, order_code, service_fee, `status`, sub_total, tax, total, billing_address_id, shipping_address_id, user_id)
VALUES (9000,'2019-10-11 00:00:00',NULL,'od01',500.74,'Returned',2002.99,140.2,2143.19,2000, 2000, 1000),
		(9001,'2019-10-11 00:00:00',NULL,'od01',228.45,'Delivering',913.83,63.96,977.79,2001, 2001,1001),
		(9002,'2019-10-12 00:00:00',NULL,'od01',37.5,'Delivering',150,10.5,160.5,2002,2002,1002),
		(9003,'2019-10-12 00:00:00',NULL,'od01',289.5,'Finished',1158,81.06,1239.06,2003,2003,1003),
		(9004,'2019-10-13 00:00:00',NULL,'od01',19.97,'New',79.9,5.59,85.49,2004,2004,1004),
		(9005,'2019-10-13 00:00:00',NULL,'od01',24.99,'Delivering',99.98,6.99,106.97,2005,2005,1005);

INSERT INTO happymall.order_line 
VALUES (6000,'2019-10-11 00:00:00','2019-10-12 00:00:00',988,2,1976,9000,4004),
		(6001,'2019-10-11 00:00:00',NULL,26.99,1,26.99,9000,4001),
		(6002,'2019-10-12 00:00:00','2019-10-13 00:00:00',105.28,3,315.84,9001,4005),
		(6003,'2019-10-12 00:00:00',NULL,18.99,1,18.99,9001,4009),
		(6004,'2019-10-12 00:00:00',NULL,579,1,579,9001,4011),
		(6005,'2019-10-12 00:00:00','2019-10-12 00:00:00',150,1,150,9002,4006),
		(6006,'2019-10-12 00:00:00','2019-10-12 00:00:00',579,2,1158,9003,4011),
		(6007,'2019-10-13 00:00:00',NULL,79.9,1,79.9,9004,4013),
		(6008,'2019-10-13 00:00:00',NULL,49.99,2,99.98,9005,4012);

INSERT INTO happymall.payment 
VALUES (7000,'Approved','2019-10-11 00:00:00','2019-10-12 00:00:00','2019-10-12 00:00:00',2143.19,3000,9000),
		(7001,'Approved','2019-10-12 00:00:00','2019-10-12 00:00:00',NULL,977.79,3001,9001),
		(7002,'Approved','2019-10-11 00:00:00','2019-10-11 00:00:00',NULL,160.5,3002,9002),
		(7003,'Denied','2019-10-12 00:00:00','2019-10-12 00:00:00',NULL,1239.06,3003,9003),
		(7004,'Denied','2019-10-11 00:00:00','2019-10-11 00:00:00',NULL,85.49,3004,9004),
		(7005,'Denied','2019-10-12 00:00:00','2019-10-12 00:00:00',NULL,106.97,3005,9005);

INSERT INTO happymall.transaction 
VALUES (8000,'2019-10-11 00:00:00','yes','2019-10-12 00:00:00',2143.19,7000),
		(8001,'2019-10-12 00:00:00','yes','2019-10-12 00:00:00',977.79,7001),
		(8002,'2019-10-11 00:00:00','yes','2019-10-12 00:00:00',160.5,7002),
		(8003,'2019-10-12 00:00:00','no','2019-10-12 00:00:00',1239.06,7003),
		(8004,'2019-10-11 00:00:00','no','2019-10-12 00:00:00',85.49,7004),
		(8005,'2019-10-12 00:00:00','no','2019-10-12 00:00:00',106.97,7005);
