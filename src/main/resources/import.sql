INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'65465465', 'Andrea');
INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'156165156', 'Felipe');
INSERT INTO worker (worker_sec,identification, name) VALUES (nextval('worker_sec_seq'),'165511132', 'Pedro');

INSERT INTO support (support_sec, description, weight, priority, assigantion_date, worker) VALUES (nextval('support_sec_seq'), 'Cambiar el agua a los peces', 5, 2, '2024-06-27', 1);