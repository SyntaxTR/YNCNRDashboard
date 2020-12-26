<?php
require($_SERVER["DOCUMENT_ROOT"] . "/Services/Helpers/SAMP/SampQuery.class.php");
require_once ($_SERVER["DOCUMENT_ROOT"] . "/Services//Helpers/JSONHelper/jsonHelper.php");
require_once ($_SERVER["DOCUMENT_ROOT"] . "/Services/Helpers/DbHelper/DbHelper.php");


$query = new SampQuery("80.93.220.90", 7777);
$serverInfo = $query->getInfo();

$db = new DbHelper();
$dt = $db->GetCounts();
while($row = mysqli_fetch_assoc($dt)){
    $serverInfo["bannedCount"] = $row["bannedCount"];
    $serverInfo["playerCount"] = $row["playerCount"];
}

$json = new JSONHelper();
$json->setArray($serverInfo);


file_put_contents("JSONFiles/Stats.json",  $json->CreateManualStatsJSON());
unset($json);
unset($serverInfo);
?>