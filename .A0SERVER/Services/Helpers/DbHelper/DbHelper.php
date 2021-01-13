<?php

class DbHelper{
	
	private $Host = "CHANGEME";
	private $username = "CHANGEME";
	private $password = "CHANGEME";
	private $db = "CHANGEME";
	private $query = null;
	private $result = null;
	
	public function __construct(){
		$this->conn = mysqli_connect($this->Host, $this->username, $this->password, $this->db);
        	
		if (!$this->conn)
            		throw new Exception("Database not connected");
    	}
	public function GetTOP15Players(){
		$this->query = "
					SELECT username, Skor
					FROM players
					WHERE
						SonGiris > UNIX_TIMESTAMP() - 15*86400
					ORDER BY Skor DESC
					LIMIT 15
		";
		$this->result = mysqli_query($this->conn, $this->query); 
		return $this->result;
	}
	public function GetCounts(){
	    $this->query = "
	                SELECT
	                (
	                    SELECT COUNT(Id)
	                    FROM bannedplayers
	                    WHERE
                            UnBannedBy IS NULL AND 
                            Status = '1'
	                )bannedCount,
	                (
	                    SELECT COUNT(Id)
	                    FROM players
	                )playerCount
	    ";
        $this->result = mysqli_query($this->conn, $this->query);
        return $this->result;
    }
    public function __destruct()
    {
        mysqli_close($this->conn);
    }
}
?>
