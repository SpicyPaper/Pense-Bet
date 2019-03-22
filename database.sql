#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: States
#------------------------------------------------------------

CREATE TABLE States(
        id   Int  Auto_increment  NOT NULL ,
        name Varchar (100) NOT NULL
    ,CONSTRAINT States_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Roles
#------------------------------------------------------------

CREATE TABLE Roles(
        id   Int  Auto_increment  NOT NULL ,
        name Varchar (100) NOT NULL
    ,CONSTRAINT Roles_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Users
#------------------------------------------------------------

CREATE TABLE Users(
        id       Int  Auto_increment  NOT NULL ,
        nickname Varchar (20) NOT NULL ,
        email    Varchar (254) NOT NULL ,
        id_Roles Int NOT NULL
    ,CONSTRAINT Users_AK UNIQUE (nickname,email)
    ,CONSTRAINT Users_PK PRIMARY KEY (id)

    ,CONSTRAINT Users_Roles_FK FOREIGN KEY (id_Roles) REFERENCES Roles(id)
)ENGINE=InnoDB;
#------------------------------------------------------------
# Table: Bets
#------------------------------------------------------------

CREATE TABLE Bets(
        id        Int  Auto_increment  NOT NULL ,
        amout     Float NOT NULL ,
        subject   Text NOT NULL ,
        result    Bool ,
        id_Users  Int NOT NULL ,
        id_States Int NOT NULL
    ,CONSTRAINT Bets_PK PRIMARY KEY (id)

    ,CONSTRAINT Bets_Users_FK FOREIGN KEY (id_Users) REFERENCES Users(id)
    ,CONSTRAINT Bets_States0_FK FOREIGN KEY (id_States) REFERENCES States(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Participate
#------------------------------------------------------------

CREATE TABLE Participate(
        id       Int NOT NULL ,
        id_Users Int NOT NULL ,
        agree    Bool NOT NULL
    ,CONSTRAINT Participate_PK PRIMARY KEY (id,id_Users)

    ,CONSTRAINT Participate_Bets_FK FOREIGN KEY (id) REFERENCES Bets(id)
    ,CONSTRAINT Participate_Users0_FK FOREIGN KEY (id_Users) REFERENCES Users(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Invite
#------------------------------------------------------------

CREATE TABLE Invite(
        id       Int NOT NULL ,
        id_Users Int NOT NULL
    ,CONSTRAINT Invite_PK PRIMARY KEY (id,id_Users)

    ,CONSTRAINT Invite_Bets_FK FOREIGN KEY (id) REFERENCES Bets(id)
    ,CONSTRAINT Invite_Users0_FK FOREIGN KEY (id_Users) REFERENCES Users(id)
)ENGINE=InnoDB;