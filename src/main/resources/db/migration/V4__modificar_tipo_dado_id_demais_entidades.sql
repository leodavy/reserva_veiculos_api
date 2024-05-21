ALTER TABLE vei_veiculo DROP COLUMN vei_nr_id CASCADE;
ALTER TABLE vei_veiculo ADD COLUMN vei_nr_id SERIAL PRIMARY KEY;

ALTER TABLE imv_imagen_veiculo DROP COLUMN imv_nr_id CASCADE;
ALTER TABLE imv_imagen_veiculo ADD COLUMN imv_nr_id SERIAL PRIMARY KEY;

ALTER TABLE vus_veiculo_usuario DROP COLUMN vus_nr_id CASCADE;
ALTER TABLE vus_veiculo_usuario ADD COLUMN vus_nr_id SERIAL PRIMARY KEY;
