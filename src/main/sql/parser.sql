CREATE TABLE tbl_esk363(
	id BIGINT NOT NULL AUTO_INCREMENT,
	rec_date DATETIME NOT NULL,
	rec_log_level VARCHAR(10) NOT NULL,
	rec_item_count INT NOT NULL,
	PRIMARY KEY(id)
) Engine InnoDB;

CREATE TABLE tbl_log_names(
	id INT NOT NULL AUTO_INCREMENT,
	log_abs_path VARCHAR(255) NOT NULL,
	log_name VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
) Engine InnoDB;
