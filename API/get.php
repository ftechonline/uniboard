<?php



ini_set('display_errors',1);
error_reporting(E_ALL);

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

$board_id = $_GET['boardId'];
$port  = $_GET['port'];

$db = new mysqli('localhost', 'root', 'password', 'uniboard');

$query = "SELECT * FROM boards b JOIN outputs o ON b.id = o.boardid JOIN modules m ON b.id = m.boardid ";

//getOutputConditions from DB

$sql = <<<SQL
    SELECT cond
    FROM `outputs`
    WHERE `boardid` = $board_id  AND `port` = $port;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}
$row = $result->fetch_assoc();
 $cond = $row['cond'] ;

//echo $cond;

//get Output from DB based on condition

$sql = <<<SQL
	SELECT * 
	FROM boards b 
	JOIN outputs o ON b.id = o.boardid
	JOIN modules m ON b.id = m.boardid
	WHERE $cond;
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}
$rows = $result->num_rows;
echo $rows;
