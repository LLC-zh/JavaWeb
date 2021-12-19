$("document").ready(function() {
	var myChart1 = echarts.init(document.getElementById('scenic_pie'));
	var myChart2 = echarts.init(document.getElementById('scenic_bar'));
	var url = 'http://localhost:8080/TravelSystem/echarts';

	$.get(url).done(function(json) {
		var list = [];
		var name = [];
		var number = [];

		for (var i = 0; i < json.length; i++) {
			var map = {
				name: json[i].scenicLocation,
				value: json[i].scenicNumber
			};
			list.push(map);

			name.push(json[i].scenicLocation);
			number.push(json[i].scenicNumber);
		}

		myChart1.setOption({
			series: {
				type: 'pie',
				data: list
			}
		});

		myChart2.setOption({
			xAxis: {
				data: name
			},
			yAxis: {},
			series: [
				{
					type: 'bar',
					data: number,
					itemStyle: {        
						normal: {
							label: {
								show: true, 
								position: 'top', 
								textStyle: { 
									color: 'black',
									fontSize: 16
								}
							}
						}
					}
				}
			]
		});
	});
});