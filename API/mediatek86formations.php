<?php

header('Content-Type: application/json');

include_once("Controle.php");

/**
 * Propriété qui récupère l'instance de Controle
 */
$controle = new Controle();

// récupération des données
// Nom de la table
$table = filter_input(INPUT_GET, 'formation', FILTER_SANITIZE_STRING) ??
        filter_input(INPUT_POST, 'formation', FILTER_SANITIZE_STRING);
// nom et valeur des champs au format json

$table = "formation";
$id = null;
$contenu = "";


// traitement suivant le verbe HTTP utilisé
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $controle->get($table, $id);
} else if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $controle->post($table, $contenu);
} else if ($_SERVER['REQUEST_METHOD'] === 'PUT') {
    $controle->put($table, $id, $contenu);
} else if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
    $controle->delete($table, $id);
}
