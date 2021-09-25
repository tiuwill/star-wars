CREATE TABLE LOCALIZACAO (
   ID INT PRIMARY KEY,
   LATITUDE VARCHAR(50) NOT NULL,
   LONGITUDE VARCHAR(20) NOT NULL,
   NOME VARCHAR(255) NOT NULL,
   GALAXIA VARCHAR(255) NOT NULL,
   BASE VARCHAR(255) NOT NULL,
   REBELDE INT NOT NULL,
   foreign key (REBELDE) references Rebelde(ID)
);
