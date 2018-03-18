<?php

$port = $_GET['port'];
$cond = $_GET['cond'];

$db = new mysqli('localhost', 'root', 'password', 'uniboard');

$statement = $db->prepare("UPDATE outputs SET cond = ? WHERE port = ?");
$statement->bind_param('si', $cond, $port );
$statement->execute();

echo "SUCCESS";
