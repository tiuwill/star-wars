CREATE TABLE ITEM_DO_REBELDE (
   ID INT PRIMARY KEY,
   ITEM INT NOT NULL,
   REBELDE INT NOT NULL,
   foreign key (ITEM) references Item(ID),
   foreign key (REBELDE) references Rebelde(ID)
);
