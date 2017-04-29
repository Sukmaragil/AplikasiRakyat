<?php 
	
	//importing dbDetails file 
	require_once 'dbConnect2.php';
	
	//this is our upload folder 
	$upload_path = 'uploads/';
	
	//Getting the server ip 
	$server_ip = gethostbyname(gethostname());
	
	//creating the upload url 
	$upload_url = 'http://'.$server_ip.'/skripsiAndroid/'.$upload_path; 
	
	//response array 
	$response = array(); 

	function acak_string( $length ) {
	$chars = "0123456789";	
 
	$size = strlen( $chars );
	for( $i = 0; $i < $length; $i++) {
		$str .= $chars[ rand( 0, $size - 1 ) ];
	}
 
	return $str;
	}
	
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//checking the required parameters from the request 
		if(isset($_FILES['ktp']['name']) and isset($_FILES['kk']['name'])){
			
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
			
			$tanggalPelayanan = $_POST['tanggalPelayanan'];
			$nik = $_POST['nik'];
			$email = $_POST['email'];
			$noPelayanan = acak_string(10);
			$pelayananId = $_POST['pelayananId'];
			$keperluan = $_POST['keperluanBelumMenikah'];


			//getting file info from the request 
			$ktp = pathinfo($_FILES['ktp']['name']);
			$kk = pathinfo($_FILES['kk']['name']);
			
			//getting the file extension 
			$extension = $ktp['extension'];
			$extension2 = $kk['extension'];
			
			$ktpName = 'ktp_' . $nik . '_' . $noPelayanan;
			$kkName = 'kk_' . $nik . '_' . $noPelayanan;

			//file url to store in the database 
			$ktpUrl = $upload_url . $ktpName . '.' . $extension;
			$kkUrl = $upload_url . $kkName . '.' . $extension2;

			
			
			//file path to upload in the server 
			$ktpPath = $upload_path . $ktpName . '.'. $extension; 
			$kkPath = $upload_path . $kkName . '.'. $extension2; 



			$query = "SELECT * FROM penduduk WHERE nik = $nik ";
			$result = mysqli_query($con,$query);
			$query2 = "INSERT INTO permohonan (nik,noPelayanan,tanggalPelayanan,email,pelayananId) VALUES ('$nik','$noPelayanan','$tanggalPelayanan','$email','$pelayananId')";

			include 'phpMailer/PHPMailerAutoload.php';

			$query5 = "SELECT namaPelayanan FROM pelayanan WHERE id = $pelayananId ";
			$hasil = mysqli_query($con,$query5);
			$baris = mysqli_fetch_array($hasil);
			$query6 = "SELECT namaDepan FROM penduduk WHERE nik = $nik";
			$hasil2 = mysqli_query($con,$query6);
			$baris2 = mysqli_fetch_array($hasil2);


			$mail = new PHPMailer();
			$mail->Host = 'smtp.gmail.com';
			$mail->Port = 587;
			$mail->SMTPSecure = 'tsl';
			$mail->SMTPAuth = true;
			$mail->Username = "kelurahan.bogor@gmail.com";
			$mail->Password = "1921sail";

			$mail->setFrom('kelurahan.bogor@gmail.com','Kelurahan Kencana');
			$mail->addAddress($email, 'Ilham Aulia');
			$mail->Subject = 'Selamat ! Permohonan '.$baris['namaPelayanan']. ' Berhasil Dilakukan';
			$mail->Body = 'Terimakasih Kepada Bpk/Ibu '.$baris2['namDepan']. ' Telah melakukan Permohonan. Berikut adalah nomer resi pelayanan '.$noPelayanan;
			
			
			//trying to save the file in the directory 
			try{
				//saving the file 
				move_uploaded_file($_FILES['ktp']['tmp_name'],$ktpPath);
				move_uploaded_file($_FILES['kk']['tmp_name'],$kkPath);
				

				$mail->send();
				mysqli_query($con,$query2);
				$query3 = "SELECT MAX(id) AS idTerakhir FROM permohonan";
				$result2 = mysqli_query($con,$query3);
				$row = mysqli_fetch_array($result2);
				$idTerakhir = $row['idTerakhir'];
				$query4 = "INSERT INTO belum_menikah (keperluan,ktp,kk,ktpUrl,kkUrl,permohonanId) VALUES ('$keperluan','$ktpName','$kkName','$ktpUrl','$kkUrl','$idTerakhir')";
				
				//adding the path and name to database 
				
					if(mysqli_query($con,$query4)){
					//filling response array with values 
					$response['error'] = false; 
					$response['url'] = $ktpPath; 
					$response['name'] = $ktp;
					$response['url2'] = $kkUrl;
					$response['name2'] = $kk;
					}
				
			//if some error occurred 
			}catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
			}		
			//displaying the response 
			
			echo json_encode($response);
			
			//closing the connection 
			mysqli_close($con);
		}else{
			$response['error']=true;
			$response['message']='Please choose a file';
		}
	}
	
	/*
		We are generating the file name 
		so this method will return a file name for the image to be upload 
	*/
	function getFileName(){
		return ktp_ . $nik;
	}

	function getFileName2(){
		return kk_ . $nik . '_' . $noPelayanan;
		
	}

