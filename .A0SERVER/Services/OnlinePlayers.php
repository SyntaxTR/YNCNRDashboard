<?php
require($_SERVER["DOCUMENT_ROOT"] . "/Services/Helpers/SAMP/SampQuery.class.php");
require_once ($_SERVER["DOCUMENT_ROOT"] . "/Services//Helpers/JSONHelper/jsonHelper.php");

$query = new SampQuery("80.93.220.90", 7777);
$players = $query->getDetailedPlayers();
$playerArray = array();
foreach ($players as $player){
	$playerArray[] = array('nickname' => $player["nickname"], 'score' => $player["score"]);
}

$json = new JSONHelper();
$json->setArray($playerArray);
file_put_contents("JSONFiles/onlinePlayers.json", $json->CreateManualJSON());
unset($json);
unset($players);
?>
