
DROP DATABASE IF EXISTS Grab_n_Go;

CREATE DATABASE Grab_n_Go;

USE  Grab_n_Go;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS member ;

DROP TABLE IF EXISTS restaurant;

DROP TABLE IF EXISTS order01 ;

DROP TABLE IF EXISTS rest_evaluate ;

DROP TABLE IF EXISTS product_type;

DROP TABLE IF EXISTS product ;

DROP TABLE IF EXISTS order_item ;

DROP TABLE IF EXISTS rest_type ;


SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE member (
	m_username VARCHAR(30) PRIMARY KEY ,
	m_password VARCHAR(50) NOT NULL ,
	m_name VARCHAR(32) NOT NULL ,
	m_phone VARCHAR(20) NOT NULL ,
	m_email VARCHAR(50) NOT NULL ,
	m_address VARCHAR(50),
	m_birthday DATE,
	m_picture LONGBLOB ,
	m_filename VARCHAR(15)
	
	
)CHARACTER SET utf8 COLLATE utf8_general_ci;





CREATE TABLE rest_type (
	
	type_name VARCHAR(10) PRIMARY KEY
	
	
) CHARACTER SET utf8 COLLATE utf8_general_ci;





CREATE TABLE restaurant (
	rest_id INT AUTO_INCREMENT PRIMARY KEY ,
	rest_type VARCHAR(10) NOT NULL,
	rest_name VARCHAR(32) UNIQUE KEY NOT NULL,
	rest_branch VARCHAR(10) ,
	rest_address VARCHAR(50) NOT NULL,
	rest_phone VARCHAR(15) NOT NULL,
	rest_owner VARCHAR(10) NOT NULL,
	rest_email VARCHAR(50) NOT NULL,
	rest_username VARCHAR(30) NOT NULL,
	rest_password VARCHAR(50) NOT NULL,
	rest_url VARCHAR(100),
	rest_longitude FLOAT,
	rest_latitude FLOAT,
	rest_mainbanner LONGBLOB,
	rest_logo LONGBLOB,
	rest_coverimage LONGBLOB,
	CONSTRAINT restaurant_rest_type_FK FOREIGN KEY (rest_type) REFERENCES rest_type(type_name) ON DELETE CASCADE

) AUTO_INCREMENT = 00001, CHARACTER SET utf8 COLLATE utf8_general_ci;





CREATE TABLE order01 (
	ord_id INT AUTO_INCREMENT PRIMARY KEY ,
	m_username VARCHAR(30) NOT NULL ,
	ord_time DATETIME,
	rest_id INT NOT NULL,
	ord_totalPrice MEDIUMINT NOT NULL,
	ord_status VARCHAR(10) NOT NULL,
	CONSTRAINT order_M_username_FK FOREIGN KEY (m_username) REFERENCES Member(m_username) ON DELETE CASCADE,
	CONSTRAINT order_rest_Id_FK FOREIGN KEY (rest_id) REFERENCES restaurant(rest_id) ON DELETE CASCADE
) AUTO_INCREMENT = 00001, CHARACTER SET utf8 COLLATE utf8_general_ci;






CREATE TABLE rest_evaluate (
	ord_id  INT PRIMARY KEY ,
	rest_id INT NOT NULL,
	restEva_star TINYINT NOT NULL,
	m_username VARCHAR(30) NOT NULL,
	restEva_comment TINYTEXT ,
	CONSTRAINT rest_evaluate_rest_id_FK FOREIGN KEY(rest_id) REFERENCES restaurant(rest_id) ON DELETE CASCADE,
	CONSTRAINT rest_evaluate_m_username_FK FOREIGN KEY(m_username) REFERENCES Member(m_username) ON DELETE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_general_ci;







CREATE TABLE product_type (
	
	rest_name VARCHAR(32) ,
	type_id  INT ,
	type_name VARCHAR(50) NOT NULL,
	PRIMARY KEY(type_id,rest_name),
	CONSTRAINT product_type_restname_FK FOREIGN KEY (rest_name) REFERENCES restaurant(rest_name) ON DELETE CASCADE
	
)  CHARACTER SET utf8 COLLATE utf8_general_ci;





CREATE TABLE product (
	
	prod_id  INT ,
	rest_id INT NOT NULL,
	type_id INT,
	prod_name VARCHAR(30) NOT NULL,
	prod_price MEDIUMINT NOT NULL,
	prod_amount SMALLINT NOT NULL,
	prod_desc TINYTEXT , 
	prod_img LONGBLOB ,
	PRIMARY KEY(prod_id,rest_id),
	CONSTRAINT prod_type_id_FK FOREIGN KEY (type_id) REFERENCES product_type(type_id) ON DELETE CASCADE,
	CONSTRAINT prod_rest_id_FK FOREIGN KEY (rest_id) REFERENCES restaurant(rest_id)ON DELETE CASCADE
	
)  CHARACTER SET utf8 COLLATE utf8_general_ci;




CREATE TABLE order_item (
	serial_no INT PRIMARY KEY AUTO_INCREMENT,
	ord_id  INT ,
	prod_id INT ,
	item_name VARCHAR(40) NOT NULL,
	item_price MEDIUMINT NOT NULL,
	item_amount SMALLINT NOT NULL,
	item_note TINYTEXT ,
	m_username VARCHAR(30) NOT NULL,
	CONSTRAINT item_ord_Id_FK FOREIGN KEY(ord_id) REFERENCES order01(ord_id) ON DELETE CASCADE,
	CONSTRAINT item_prod_Id_FK FOREIGN KEY(prod_id) REFERENCES product(prod_id)ON DELETE CASCADE,
	CONSTRAINT item_m_username_FK FOREIGN KEY(m_username) REFERENCES member(m_username)ON DELETE CASCADE
)  CHARACTER SET utf8 COLLATE utf8_general_ci;





