Demo sur l'utilisation d'un cas simple de Spring Batch: 

	- Lecture d'un fichier csv en entrée, et écriture en base de données H2.

	- Le job par défaut n'est pas exécuté au démarrage (spring.batch.job.enabled=false), 

	  il sera démarré via une action dans une API REST.
	  
	- Un traitement pour l'écriture de la date de transaction, 
	 
	  et un deuxième pour le calcul du total des montants débit/crédit.


	+ Pour exécuter le job :					http://localhost:8080/startJob
	+ Renvoyer les montants débit et crédit :	http://localhost:8080/amounts	
	+ Visualiser les transactions en BD :		http://localhost:8080/h2-console
	

			