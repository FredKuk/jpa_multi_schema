-- 고객
use JADEN;
CREATE TABLE customer (
	cusNo INTEGER NOT NULL, -- 고객번호,
	name VARCHAR(255) NOT NULL, -- 이름,
	id VARCHAR(255) NOT NULL, -- 아이디,
	pwd VARCHAR(255) NOT NULL, -- 비밀번호,
	startDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 가입날짜,
	changeDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, -- 변경날짜,
	stat INTEGER NOT NULL -- 상태
);

-- 고객
ALTER TABLE customer
	ADD CONSTRAINT PK_customer -- 고객 Primary key
	PRIMARY KEY (
		cusNo -- 고객번호
	);

ALTER TABLE customer
	MODIFY COLUMN cusNo INTEGER NOT NULL AUTO_INCREMENT;

-- 상품
CREATE TABLE good (
	goodNo INTEGER NOT NULL, -- 상품번호,
	name VARCHAR(255) NOT NULL, -- 상품이름,
	type INTEGER NOT NULL, -- 상품종류,
	company VARCHAR(255) NOT NULL, -- 상품회사,
	startDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 출시날짜,
	changeDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, -- 변경날짜,
	stat INTEGER NOT NULL -- 상태
);

-- 상품
ALTER TABLE good
	ADD CONSTRAINT PK_good -- 상품 Primary key
	PRIMARY KEY (
		goodNo -- 상품번호
	);

ALTER TABLE good
	MODIFY COLUMN goodNo INTEGER NOT NULL AUTO_INCREMENT;


-- 가입상품
CREATE TABLE gJoin (
	gJoinNo INTEGER NOT NULL, -- 가입상품번호,
	goodNo INTEGER NOT NULL, -- 상품번호,
	cusNo INTEGER NOT NULL, -- 고객번호,
	startDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 가입날짜,
	changeDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, -- 변경날짜,
	stat INTEGER NOT NULL -- 상태
);

-- 가입상품
ALTER TABLE gJoin
	ADD CONSTRAINT PK_gJoin -- 가입상품 Primary key
	PRIMARY KEY (
		gJoinNo -- 가입상품번호
	);

ALTER TABLE gJoin
	MODIFY COLUMN gJoinNo INTEGER NOT NULL AUTO_INCREMENT;

-- 상품리뷰
CREATE TABLE gReview (
	gReviewNo INTEGER NOT NULL, -- 상품리뷰번호,
	goodNo INTEGER NOT NULL, -- 상품번호,
	cusNo INTEGER NOT NULL, -- 고객번호,
	subject VARCHAR(255) NOT NULL, -- 제목,
	contents VARCHAR(255) NOT NULL, -- 내용,
	startDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 작성날짜,
	changeDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, -- 변경날짜,
	stat INTEGER NOT NULL -- 상태
);

-- 상품리뷰
ALTER TABLE gReview
	ADD CONSTRAINT PK_gReview -- 상품리뷰 Primary key
	PRIMARY KEY (
		gReviewNo -- 상품리뷰번호
	);

ALTER TABLE gReview
	MODIFY COLUMN gReviewNo INTEGER NOT NULL AUTO_INCREMENT;

-- 상품댓글
CREATE TABLE gComment (
	gCommentNo INTEGER NOT NULL, -- 상품댓글번호,
	gReviewNo INTEGER NOT NULL, -- 상품리뷰번호,
	cusId INTEGER NOT NULL, -- 고객번호,
	subject VARCHAR(255) NOT NULL, -- 제목,
	contents VARCHAR(255) NOT NULL, -- 내용,
	startDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 작성날짜,
	changeDate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, -- 변경날짜,
	stat INTEGER NOT NULL -- 상태
);

-- 상품댓글
ALTER TABLE gComment
	ADD CONSTRAINT PK_gComment -- 상품댓글 Primary key
	PRIMARY KEY (
		gCommentNo -- 상품댓글번호
	);

ALTER TABLE gComment
	MODIFY COLUMN gCommentNo INTEGER NOT NULL AUTO_INCREMENT;

-- 가입상품
ALTER TABLE gJoin
	ADD CONSTRAINT FK_good_TO_gJoin -- 상품 -> 가입상품
	FOREIGN KEY (
		goodNo -- 상품번호
	)
	REFERENCES good ( -- 상품
		goodNo -- 상품번호
	);

-- 가입상품
ALTER TABLE gJoin
	ADD CONSTRAINT FK_customer_TO_gJoin -- 고객 -> 가입상품
	FOREIGN KEY (
		cusNo -- 고객번호
	)
	REFERENCES customer ( -- 고객
		cusNo -- 고객번호
	);

-- 상품리뷰
ALTER TABLE gReview
	ADD CONSTRAINT FK_good_TO_gReview -- 상품 -> 상품리뷰
	FOREIGN KEY (
		goodNo -- 상품번호
	)
	REFERENCES good ( -- 상품
		goodNo -- 상품번호
	);

-- 상품리뷰
ALTER TABLE gReview
	ADD CONSTRAINT FK_customer_TO_gReview -- 고객 -> 상품리뷰
	FOREIGN KEY (
		cusNo -- 고객번호
	)
	REFERENCES customer ( -- 고객
		cusNo -- 고객번호
	);

-- 상품댓글
ALTER TABLE gComment
	ADD CONSTRAINT FK_gReview_TO_gComment -- 상품리뷰 -> 상품댓글
	FOREIGN KEY (
		gReviewNo -- 상품리뷰번호
	)
	REFERENCES gReview ( -- 상품리뷰
		gReviewNo -- 상품리뷰번호
	);
    
    
INSERT INTO customer(name,id,pwd,startDate,changeDate,stat)
VALUES ('K','jaden','1111',DEFAULT,DEFAULT,1);
INSERT INTO customer(name,id,pwd,startDate,changeDate,stat)
VALUES ('P','jay','1111',DEFAULT,DEFAULT,1);
INSERT INTO customer(name,id,pwd,startDate,changeDate,stat)
VALUES ('G','metal','1111',DEFAULT,DEFAULT,1);
INSERT INTO customer(name,id,pwd,startDate,changeDate,stat)
VALUES ('B','tony','1111',DEFAULT,DEFAULT,1);
COMMIT;