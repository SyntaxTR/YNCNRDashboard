<?php
class JSONHelper{
    private $array = null;

    public function setArray(array $array){
        if(count($array) == 0)
            throw new Exception("Array size must be  > 0 ");

        $this->array = $array;
    }
    public function getArray(){
        return $this->array;
    }

    public function CreateManualJSON(){
        $jsontext = '{"players":[';

        foreach ($this->array as $player){
            $jsontext .= '{"Nick": "'.$player["nickname"].'", "Score": "'.$player["score"].'"},';
        }
        $jsontext = substr_replace($jsontext, '', -1);
        $jsontext .= "]}";
        return $jsontext;
    }

    public function CreateManualStatsJSON(){
        $jsontext = '{"stats":[{';

        foreach ($this->array as $key => $val)
            $jsontext .= '"'.$key.'": "'.$val.'",';


        $jsontext = substr_replace($jsontext, '', -1);
        $jsontext .= "}]}";
        return $jsontext;
    }
    public function createJSON(){
        return json_encode($this->array);
    }

}