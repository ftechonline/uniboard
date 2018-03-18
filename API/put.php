<?php

$board_id = $_GET['boardId'];
$a_value  = $_GET['aValue'];
$d_value  = $_GET['dValue'];

$db = new mysqli('localhost', 'root', 'password', 'uniboard');

$statement = $db->prepare("UPDATE modules SET analogValue = ? , digitalValue = ? WHERE boardId = ?");
$statement->bind_param('sss', $a_value, $d_value, $board_id );
$statement->execute();



$port = 0;

$query = "SELECT * FROM boards b JOIN outputs o ON b.id = o.boardid JOIN modules m ON b.id = m.boardid ";

//getOutputConditions from DB

$sql = <<<SQL
    SELECT cond
    FROM `outputs`
    WHERE `boardid` = $board_id  AND `port` = $port;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query1 [' . $db->error . ']');
}
$row = $result->fetch_assoc();
 $cond = $row['cond'] ;

//echo $cond;

//get Output from DB based on condition

if(isset($cond)){

$sql = <<<SQL
	SELECT * 
	FROM boards b 
	JOIN outputs o ON b.id = o.boardid
	JOIN modules m ON b.id = m.boardid
	WHERE port=$port AND $cond;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}
$rows = $result->num_rows;
echo $rows;

}else{
echo "0";
}





$port = 1;

$query = "SELECT * FROM boards b JOIN outputs o ON b.id = o.boardid JOIN modules m ON b.id = m.boardid ";

//getOutputConditions from DB

$sql = <<<SQL
    SELECT cond
    FROM `outputs`
    WHERE `boardid` = $board_id  AND `port` = $port;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query1 [' . $db->error . ']');
}
$row = $result->fetch_assoc();
 $cond = $row['cond'] ;


//get Output from DB based on condition

if(isset($cond)){

$sql = <<<SQL
	SELECT * 
	FROM boards b 
	JOIN outputs o ON b.id = o.boardid
	JOIN modules m ON b.id = m.boardid
	WHERE port=$port AND $cond;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}
$rows = $result->num_rows;
echo $rows;

}else{
echo "0";
}





