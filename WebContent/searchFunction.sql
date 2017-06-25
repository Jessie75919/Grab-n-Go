DELIMITER //

DROP PROCEDURE IF EXISTS get_Rest //

CREATE PROCEDURE get_Rest( lat DOUBLE, longi DOUBLE )
BEGIN  
   SELECT a.rest_id AS rest, 
	111.111 *
	DEGREES(ACOS(COS(RADIANS(a.rest_latitude))
		 * COS(RADIANS(lat))
		 * COS(RADIANS(a.rest_longitude - longi))
		 + SIN(RADIANS(a.rest_latitude))
		 * SIN(RADIANS(lat)))) AS distance_in_km
    FROM restaurant AS a
    HAVING distance_in_km < 1;
    
END 
//
DELIMITER ;
 
