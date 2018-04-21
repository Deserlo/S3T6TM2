insert into User (id, userName, fname, team, pwd, mgrID)
values
("1", "faucibus@ametlorem.net","Guy Burns", "Analysis", "CKE50UJF0MV", "27"),
("2", "malesuada.fringilla@ametloremsemper.net", "Pascale Chase", "BackEnd", "HRY83AYY2MQ", "16"),
("3", "varius@Aliquamtincidunt.edu", "Wade Hendrix", "FrontEnd", "PQY74LEO0XP", "15"),
("4", "accumsan.neque.et@Duisacarcu.com", "Ruby Douglas", "Testing", "ZKY28TEW7GN", "14"),

("5", "eu.lacus.Quisque@odioapurus.net", "Noah Potter", "FrontEnd", "OXK48PXR7RB", "15"),
("6", "Praesent@Cumsociis.edu", "Beverly Ewing", "Testing", "EIN67KYB4OY", "14"),
("7", "faucibus.orci@vitaeorci.org", "Adam Mcclure", "BackEnd", "EGM54GDF2PL", "16"),
("8", "vel.nisl@duiSuspendisse.org", "Lee Barton", "Design", "ZNW08FXX6CW", "9"),
("9", "tempus.lorem@lobortistellusjusto.edu", "Mira Pope", "Manager", "ABF69BZN4FZ", null),
("10", "malesuada.Integer.id@sed.edu", "Callum Harmon", "Testing", "YZI18DAD0MT", "14"),

("11", "dis.parturient@Integerurna.co.uk", "Channing Manning", "FrontEnd", "XWB01XXG1AS", "15"),
("12", "Sed.neque@ullamcorpervelitin.com",	"Berk Marks",	"FrontEnd",	"BPF27UPJ3AS", "15"),
("13", "Aenean@Integer.co.uk",	"Mannix Kemp",	"Analysis",	"BTH49EYN0UB",	"27"),
("14", "auctor.ullamcorper.nisl@libero.co.uk",	"Asher Pearson",	"Manager",	"SNM19UNG3UD", null),
("15", "natoque.penatibus@iaculisnec.edu",	"Rylee Nguyen",	"Manager",	"HMN23PKH8FQ", null	),
("16",	"fringilla.ornare.placerat@placeratCras.ca",	"Simon Pratt",	"Manager",	"GNI13UOM9VB",	null),
("17",	"sociosqu.ad.litora@eratvolutpat.com",	"Chastity Stanley",	"BackEnd",	"QHJ85DGT4EF",	"16"),

("18",	"Cum@ut.co.uk",	"Gavin Chavez",	"Design",	"PMF66BXQ6YU",	"9"),
("19",	"lorem@a.co.uk",	"Gretchen Wiley",	"Testing",	"TOZ04XXE9UW",	"14"),
("20",	"posuere.a@atarcuVestibulum.edu",	"April Atkinson",	"FrontEnd",	"FDC59MJK2NF",	"15"),
("21",	"ut@sitametante.co.uk",	"Hiroko Kaufman",	"Analysis",	"ZQS23AJU7KY",	"27"),
("22",	"tellus@ultrices.com",	"Tobias Keith",	"Testing",	"TTQ31WOM3GD",	"14"),
("23",	"dictum.Proin@acurna.com",	"Illiana Lamb",	"BackEnd",	"SSB37RRE1UL",	"16"),
("24",	"pretium.aliquet@tinciduntadipiscingMauris.net",	"Chiquita Olsen",	"Analysis",	"CGA99JKD5LL",	"27"),
("25",	"nec@enimnonnisi.com",	"Bradley Peters",	"Design",	"JXT81TZI3WZ",	"9"),
("26",	"non.dapibus.rutrum@Vivamusnibhdolor.org",	"Nomlanga Patrick",	"FrontEnd",	"SOW75YTE7HS",	"15"),
("27",	"ultrices.sit.amet@porttitorscelerisque.edu",	"Hanna Shelton",	"Manager",	"AML77HEF3BC", null),
("28",	"enim@sitametconsectetuer.net",	"Whoopi Summers",	"Testing",	"DSR23QRX2ST",	"14"),
("29",	"magna@aliquetsem.ca",	"Vielka Pratt",	"FrontEnd",	"MDV94LWQ4VF",	"15"),
("30",	"magna.Praesent@consectetueradipiscingelit.org",	"Jerry Kinney",	"BackEnd", "IKK23ZQW7MM",	"16");

#all usernames +4##

insert into Developer (userName, id)
values
("faucibus@ametlorem.net", "1"),
("malesuada.fringilla@ametloremsemper.net", "2"),
("varius@Aliquamtincidunt.edu", "3"),
("accumsan.neque.et@Duisacarcu.com", "4"),
("eu.lacus.Quisque@odioapurus.net", "5"),
("Praesent@Cumsociis.edu", "6"),
("faucibus.orci@vitaeorci.org", "7"),
("vel.nisl@duiSuspendisse.org", "8"),
("malesuada.Integer.id@sed.edu", "10"),
("dis.parturient@Integerurna.co.uk", "11"),
("Sed.neque@ullamcorpervelitin.com", "12"),
("Aenean@Integer.co.uk", "13"),
("sociosqu.ad.litora@eratvolutpat.com", "17"),
("Cum@ut.co.uk", "18"),
("lorem@a.co.uk", "19"),
("posuere.at@atarcuVestibulum.edu", "20"),
("ut@sitametante.co.uk", "21"),
("tellus@ultrices.com", "22"),
("dictum.Proin@acurna.com", "23"),
("pretium.aliquet@tinciduntadipiscingMauris.net", "24"),
("nec@enimnonnisi.com", "25"),
("non.dapibus.rutrum@Vivamusnibhdolor.org", "26"),
("enim@sitametconsectetuer.net", "28"),
("magna@aliquetsem.ca", "29"),
("magna.Praesent@consectetueradipiscingelit.org", "30");

insert into Manager (userName, id)
values
("tempus.lorem@lobortistellusjusto.edu",	"9"),
("auctor.ullamcorper.nisl@libero.co.uk",	"14"),
("natoque.penatibus@iaculisnec.edu",	"15"),
("fringilla.ornare.placerat@placeratCras.ca",	"16"),
("ultrices.sit.amet@porttitorscelerisque.edu",	"27");

insert into Project (projNo, mgrID, dueDate)
values
("567",	"9",	"09/18/2018"),
("568",	"27",	"02/27/2019"),
("569",	"9",	"12/21/2018"),
("570",	"14",	"10/16/2018"),
("571",	"16",	"12/20/2018"),
("572",	"27",	"04/10/2018"),
("573",	"15",	"09/15/2018"),
("574",	"16",	"05/16/2018"),
("575",	"15",	"04/02/2019");

insert into Works_on (projNo, devID)
values
("568",	"1"),
("571",	"2"),
("573",	"3"),
("570",	"4"),
("573",	"5"),
("570",	"6"),
("571",	"7"),
("567",	"8"),
("570",	"10"),
("573",	"11"),
("573",	"12"),
("568",	"13"),
("574",	"17"),
("567",	"18"),
("570",	"19"),
("575",	"20"),
("572",	"21"),
("570",	"22"),
("572",	"23"),
("572",	"24"),
("569",	"25"),
("575",	"26"),
("570",	"28"),
("575",	"29"),
("574",	"30");

insert into Task (taskID, taskName, projNo, devID, start, end, duration, description)
values
("1",	"UI/UX",	"567",	"8",	"9:15",	"12:45",	"3:30:00",	"description"),
("2",	"Architecture design",	"571",	"7",	"16:23",	"18:05",	"1:42:00",	"rutrum urna, nec luctus felis purus ac tellus"),
("3",	"Database",	"572",	"21",	"13:00",	"15:30",	"2:30:00",	"taciti sociosqu ad litora torquent per conubia nostra, per"),
("4",	"Development",	"568",	"1",	"14:05",	"16:40",	"2:35:00",	"dapibus gravida. Aliquam"),
("5",	"Requirements",	"574",	"17",	"10:10",	"12:00",	"1:50:00",	"ac nulla. I tincidunt congue turpis. I condimentum"),
("6",	"Test design",	"568",	"13",	"9:30",	"11:00",	"1:30:00",	"pede. Cum sociis natoque penatibus"),
("7",	"Database",	"567",	"18",	"9:45",	"13:00",	"3:15:00",	"dis parturient montes, nascetur ridiculus mus. Donec dignissim magna"),
("8",	"Test functionality",	"570",	"10",	"10:00",	"10:30",	"0:30:00", "ornare sagittis felis. Donec tempor, est ac mattis"),
("9",	"Development",	"567",	"6",	"15:00",	"16:00",	"1:00:00",	"neque sed sem egestas blandit. Nam nulla"),
("10",	"Architecture design",	"570", "4",	"13:40",	"14:15",	"0:35:00",	"Aliquam gravida mauris ut mi. Duis"),

("11",	"Requirements",	"571",	"2",	"12:00",	"16:00",	"4:00:00",	"quis turpis vitae purus"),
("12",	"UI/UX design",	"568",	"1",	"11:30",	"14:45",	"3:15:00",	"nec orci. Donec nibh. Quisque nonummy ipsum non"),
("13",	"Development",	"572",	"23",	"9:10",	"10:00",	"0:50:00",	"ornare. Fusce mollis. Duis sit"),
("14",	"Development",	"570",	"19",	"17:00",	"17:45",	"0:45:00",	"felis. Nulla tempor"),
("15",	"Test design",	"574",	"30",	"11:03",	"13:45",	"2:42:00",	"congue, elit sed consequat auctor, nunc nulla vulputate"),
("16",	"Development",	"567",	"8",	"12:00",	"14:20",	"2:20:00",	"nulla. Intege urna. Vivamus molestie dapibus"),
("17",	"Development",	"574",	"17",	"13:00",	"17:30",	"4:30:00",	"neque sed sem egestas blandit. Nam nulla"),
("18",	"Requirements",	"569",	"25",	"9:30",	"13:40",	"4:10:00",	"diam. Proin dolor. Nulla semper"),
("19",	"Architecture design",	"571",	"7",	"12:30",	"12:45",	"0:15:00",	"Phasellus dolor elit, pellentesque a, facilisis non, bibendum sed"),

("20",	"Test functionality",	"573",	"3",	"11:05",	"13:40",	"2:35:00",	"erat vel pede blandit congue. I scelerisque scelerisque"),
("21",	"Database",	"567",	"18",	"14:50",	"16:30",	"1:40:00",	"lectus justo eu arcu. Morbi sit amet"),
("22",	"Development",	"570",	"4",	"11:20",	"15:40",	"4:20:00",	"Sed diam lorem, auctor quis"),
("23",	"Architecture design",	"572",	"24",	"9:45",	"14:15",	"4:30:00",	"sem ut dolor dapibus gravida. Aliquam tincidunt, nunc ac"),
("24",	"Test functionality",	"571",	"2",	"15:00",	"16:45",	"1:45:00",	"Phasellus dolor elit, pellentesque a, facilisis non, bibendum sed"),
("25",	"Development",	"568",	"13",	"13:30",	"14:45",	"1:15:00",	"cursus et, magna. Praesent interdum ligula"),
("26",	"Requirements",	"573",	"12",	"11:00",	"15:40",	"4:40:00",	"libero nec ligula consectetuer"),

("27",	"Development",	"575",	"19","12:45",	"16:30",	"3:45:00",	"quam. Pellentesque habitant morbi"),

("28",	"Development",	"569",	"20",	"10:30",	"15:00",	"4:30:00",	"massa. Vestibulum accumsan neque et"),
("29",	"Architecture design",	"570",	"22",	"12:45",	"13:55",	"1:10:00",	"sapien. Nunc pulvinar arcu"),
("30",	"Requirements",	"573",	"5",	"11:00",	"13:00",	"2:00:00",	"I faucibus. Morbi"),
("31",	"UI/UX design",	"575",	"26",	"9:00",	"10:45",	"1:45:00",	"et nunc. Quisque ornare tortor at risus. Nunc"),
("32",	"Development",	"568",	"1",	"14:05",	"16:40",	"2:35:00",	"arcu. Sed eu nibh vulputate mauris sagittis placerat. Cras"),
("33",	"Architecture design",	"574",	"30",	"12:30",	"13:00",	"0:30:00",	"fames ac turpis egestas. Fusce aliquet magna a neque"),
("34",	"Test design",	"575",	"29",	"11:00",	"14:30",	"3:30:00",	"Duis gravida. Praesent eu nulla at"),
("35",	"Development",	"572",	"21",	"15:00",	"17:30",	"2:30:00",	"lorem vitae odio sagittis semper. Nam tempor diam"),
("36",	"Development",	"574",	"17",	"9:00",	"16:00",	"7:00:00",	"gravida nunc sed pede"),
("37",	"Development",	"567",	"8",	"16:15",	"17:45",	"1:30:00",	"id, ante. Nunc"),
("38",	"Test design",	"573",	"11",	"13:00",	"14:50",	"1:50:00",	"erat nonummy ultricies ornare, elit elit"),
("39",	"Requirements",	"570",	"28",	"12:05",	"16:00",	"3:55:00",	"Aenean sed pede nec"),
("40",	"Architecture design",	"570",	"19",	"9:00",	"10:30",	"1:30:00",	"aliquet. Proin velit. Sed malesuada augue");

insert into Team (teamID, teamName, mgrID)
values
("343097",	"Analysis",	"27"),
("154648",	"Testing",	"14"),
("147809",	"FrontEnd",	"15"),
("346512",	"BackEnd",	"16"),
("905637",	"Design",	"9");
