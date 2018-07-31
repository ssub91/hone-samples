-- 가장 기본이 되는 저장 프로시저 my_proc

CREATE OR REPLACE PROCEDURE MY_PROC AS
BEGIN
	null;
END MY_PROC;


-- 같은 이름의 프로시저를 생성할 때는 OR REPLACE 를 사용한다.

CREATE OR REPLACE PROCEDURE MY_PROC AS
BEGIN
	dbms_output.put_line( 'Hello World' );
END MY_PROC;
