TODO : 
Vérifier le cas de test
Vérifier comportement de SeqFilter

# Problème cas de test de base
	- La taille du flot d'entrée doit être un multiple de NB_OF_THREADS=4 (sinon certaines valeurs du flot ne seront pas traitées
	- Etant donné l'utilisation s.length comme Fonction de test, il faudrait que la taille des chaines soit tjrs comprise entre 0 et MAX_VALUE = 10
	- Il faut attendre la fin de l'execution de flot.filter avant de vérifier l'oracle (sinon des traitement seront en cours et donc tous les résultats ne seront pas encore dans le flow de sortie)
	
