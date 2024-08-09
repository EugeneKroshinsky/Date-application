CREATE TABLE date_idea(
	id serial PRIMARY KEY,
    date_of_creation date NOT NULL,
	country_id INT NOT NULL,
	region_id INT NOT NULL,
	city_id INT NOT NULL,
	address VARCHAR NOT NULL,
	type_id INT NOT NULL,
	minPrice INT,
	maxPrice INT,
	description TEXT NOT NULL,
	CONSTRAINT fk_country FOREIGN KEY(country_id) REFERENCES country(id),
	CONSTRAINT fk_city FOREIGN KEY(city_id) REFERENCES city(id),
	CONSTRAINT fk_type FOREIGN KEY(type_id) REFERENCES types(id),
	CONSTRAINT fk_region FOREIGN KEY(region_id) REFERENCES region(id)
)