<?php
require_once ($_SERVER["DOCUMENT_ROOT"] . "/Services/Helpers/DbHelper/DbHelper.php");
require_once ($_SERVER["DOCUMENT_ROOT"] . "/Services//Helpers/JSONHelper/jsonHelper.php");

$db = new DbHelper();
$dt = $db->GetTOP15Players();

$playerArray = array();
while($row = mysqli_fetch_assoc($dt)){
    $playerArray[] = array('nickname' => $row["username"], 'score' => $row["Skor"]);
}
$json = new JSONHelper();
$json->setArray($playerArray);
file_put_contents("JSONFiles/TOP15Players.json", $json->CreateManualJSON());
unset($db);
unset($json);
?>