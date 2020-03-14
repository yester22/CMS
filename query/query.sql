#maria db

CREATE TABLE `t_message` (
  `MSG_KEY` varchar(14) NOT NULL,
  `NATION_CODE` varchar(10) NOT NULL,
  `MSG_TYPE` varchar(15) DEFAULT NULL,
  `MESSAGE` varchar(255) NOT NULL,
  PRIMARY KEY `PK_MESSAGE` (`MSG_KEY`,`NATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000001', 'kr', 'LGN', 'Login'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000002', 'kr', 'LGN', '계정이 존재하지 않습니다'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000003', 'kr', 'LGN', '암호가 맞지 않습니다'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000004', 'kr', 'LGN', 'ID 저장'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000005', 'kr', 'LGN', 'ID(email)'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000006', 'kr', 'LGN', 'Password'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'LGN0000007', 'kr', 'LGN', '로그인 정보를 입력하세요'  );
insert into t_message( MSG_KEY,NATION_CODE,MSG_TYPE,MESSAGE ) values( 'CFM0000001', 'kr', 'MSGBOX', 'Message'  );


CREATE TABLE `T_MEMBER` (
  MEMBER_ID varchar(30) NOT NULL,
  MEMBER_NM varchar(30) NOT NULL,
  MEMBER_TYPE VARCHAR(20) NOT NULL,
  PWD VARCHAR(255) NOT NULL,
  CHK_ID_CODE VARCHAR(255) NULL,
  CHK_PW_CODE VARCHAR(255) NULL,
  LOGIN_FAIL_CNT BIGINT(13) NOT NULL DEFAULT 0, 
  LAST_LOGIN_DT  DATETIME NULL, 
  LAST_LOGOUT_DT DATETIME NULL,
  UPT_DATE DATETIME,
  PRIMARY KEY `PK_MEMBER` (`MEMBER_ID` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO T_MEMBER ( MEMBER_ID, MEMBER_NM, MEMBER_TYPE, PWD ) VALUES ( 'letter20@nate.com', '정경진', 'ADMIN', 'QEuiVKRicPyKBDM1V5pGZw==!' );

CREATE TABLE `t_login_try` (
  `LOGIN_DATE` varchar(30) NOT NULL,
  `MEMBER_ID` varchar(30) NOT NULL,
  `IP_ADDR` varchar(20) DEFAULT NULL,
  `NIC_ADDR` varchar(30) DEFAULT NULL,
  `LOGIN_PW` varchar(50) NOT NULL,
  `SUCCESS_YN` varchar(1) NOT NULL,
  PRIMARY KEY (`LOGIN_DATE`,`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

