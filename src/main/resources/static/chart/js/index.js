// JavaScript Document
// echarts
// create for AgnesXu at 20161115



//环状图

function pieset() {
    var ring = echarts.init(document.getElementById('ring'));
    var labelTop = {
        normal : {
            label : {
                show : true,
                position : 'center',
                formatter : '{b}',
                textStyle: {
                    baseline : 'bottom'
                }
            },
            labelLine : {
                show : false
            }
        }
    };

    var labelFromatter = {
        normal : {
            label : {
                formatter : function (params){
                    return 100 - params.value + '%'
                },
                textStyle: {
                    baseline : 'top'
                }
            }
        }
    };
    var labelBottom = {
        normal : {
            color: '#fff',
            label : {
                show : true,
                position : 'center'
            },
            labelLine : {
                show : false
            }
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var radius = [40, 55];
    ring.setOption({
        color:["#33bb9f","#ffa259","#4cbbe6"],
        series : [
            {
                type : 'pie',
                center : ['15%', '58%'],
                radius : radius,
                x: '0%', // for funnel
                itemStyle : labelFromatter,
                data : [
                    {name:'other', value:100-workerper, itemStyle : labelBottom},
                    {name:'工人', value:workerper,itemStyle : labelTop}
                ]
            },
            {
                type : 'pie',
                center : ['45%', '58%'],
                radius : radius,
                x:'20%', // for funnel
                itemStyle : labelFromatter,
                data : [
                    {name:'other', value:100-announcersper, itemStyle : labelBottom},
                    {name:'发布者', value:announcersper,itemStyle : labelTop}
                ]
            },
            {
                type : 'pie',
                center : ['75%', '58%'],
                radius : radius,
                x:'40%', // for funnel
                itemStyle : labelFromatter,
                data : [
                    {name:'other', value:100-raterper, itemStyle : labelBottom},
                    {name:'审评员', value:raterper,itemStyle : labelTop}
                ]
            }
        ]
    }) ;

}




//折线图
function lineset() {
    var line = echarts.init(document.getElementById('line'));
    line.setOption({
        color:["#32d2c9"],
        title: {
            subtext: '任务数（个）'
        },
        tooltip: {
            trigger: 'axis'
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data:  ['前6天','前5天','前4天','前3天','前2天','前1天','今天'],
            axisLabel: {
                interval:0
            },
            axisLine:{
                lineStyle:{
                    color:'#fff'
                }
            }
        },
        yAxis: {
            type: 'value',
            max: 10,
            min: 0,
            axisLine:{
                lineStyle:{
                    color:'#fff'
                }
            }
        },
        series: [
            {
                name:'发布任务个数',
                type:'line',
                data:array_released,
                markLine: {data: [{type: 'average', name: '平均值'}]}
            }

        ]
    }) ;
}


//柱状图
function pillarset1() {
    var pillar1 = echarts.init(document.getElementById('pillar1'));
    pillar1.setOption({
        color:["#ce6e73","#ee804b","#ffc668"],
        title : {
            subtext: '登录次数（次）'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            x: 'right',
            data:['工人','发布者','审批者']
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['前6天','前5天','前4天','前3天','前2天','前1天','今天'],
                axisLine:{
                    lineStyle:{
                        color:'#fff'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                max: 10,
                min: 0,
                axisLine:{
                    lineStyle:{
                        color:'#fff'
                    }
                }
            }
        ],
        series : [
            {
                name:'工人',
                type:'bar',
                data:array_login3
            },
            {
                name:'发布者',
                type:'bar',
                data:array_login
            },
            {
                name:'审批员',
                type:'bar',
                data:array_login2
            }
        ]
    }) ;
}



//柱状图2
function pillarset2() {
    var pillar2 = echarts.init(document.getElementById('pillar2'));
    pillar2.setOption({
        color:["#00afff"],
        title : {
            subtext: '任务数（个）'
        },
        tooltip : {
            trigger: 'axis'
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['前6天','前5天','前4天','前3天','前2天','前1天','今天'],
                axisLine:{
                    lineStyle:{
                        color:'#fff'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                max: 10,
                min: 0,
                axisLine:{
                    lineStyle:{
                        color:'#fff'
                    }
                }
            }
        ],
        series : [
            {
                name:'任务数',
                type:'bar',
                data:array_finished
            }
        ]
    });

}
