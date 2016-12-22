		window.onload = function(){
			 //alert(myDate.getDate());
			 showTime();
		}
		function checkTime(i){
				if(i<10){
					i = "0"+i;
				}
					return i;
				

			}
		function showTime(){
			
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = myDate.getMonth()+1;
			var date = myDate.getDate();
			var d = myDate.getDay();
			var h = myDate.getHours();
			var m = myDate.getMinutes();
			var s = myDate.getSeconds();
			m = checkTime(m);
			s = checkTime(s);
			var weekday = new Array(7);
			weekday[0] = "星期天"
			weekday[1] = "星期一"
			weekday[2] = "星期二"
			weekday[3] = "星期三"
			weekday[4] = "星期四"
			weekday[5] = "星期五"
			weekday[6] = "星期六"
			document.getElementById('show').innerHTML = year+'年'+month+'月'+date+'日'+weekday[d]+h+':'+m+':'+s;
			// alert(myDate.getFullYear());
			// alert(myDate.getMonth()+1);
			//
			setTimeout(showTime,500);
		}