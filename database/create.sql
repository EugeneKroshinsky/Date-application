CREATE TABLE countries(
	id serial PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);
CREATE TABLE cities(
	id serial PRIMARY KEY,
	country_id INT NOT NULL,
	name VARCHAR(30) NOT NULL,
	CONSTRAINT fk_country_city FOREIGN KEY(country_id) REFERENCES countries(id)
);
CREATE TABLE types(
	id serial PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE date_idea(
	id serial PRIMARY KEY,
    date_of_creation date NOT NULL,
	country_id INT NOT NULL,
	city_id INT NOT NULL,
	address VARCHAR NOT NULL,
	type_id INT NOT NULL,
	minPrice INT,
	maxPrice INT,
	description TEXT NOT NULL,
	CONSTRAINT fk_country FOREIGN KEY(country_id) REFERENCES countries(id),
	CONSTRAINT fk_city FOREIGN KEY(city_id) REFERENCES cities(id),
	CONSTRAINT fk_type FOREIGN KEY(type_id) REFERENCES types(id)
);