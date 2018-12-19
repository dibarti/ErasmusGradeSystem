/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 88.03813545343581, "KoPercent": 11.96186454656418};
    var dataset = [
        {
            "label" : "KO",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "OK",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.15937056708342956, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.1513353115727003, 500, 1500, "Login as Teacher-1"], "isController": false}, {"data": [0.19310659666742752, 500, 1500, "Login as Teacher-0"], "isController": false}, {"data": [0.11727183513248282, 500, 1500, "Goto Testing activity"], "isController": false}, {"data": [0.1412193850964044, 500, 1500, "Goto Testing Project student list"], "isController": false}, {"data": [0.2331390507910075, 500, 1500, "Goto Testing activity-0"], "isController": false}, {"data": [0.17055870798777825, 500, 1500, "Visit website"], "isController": false}, {"data": [0.28726061615320564, 500, 1500, "Goto Testing activity-1"], "isController": false}, {"data": [0.061059118922620406, 500, 1500, "Login as Teacher"], "isController": false}, {"data": [0.31294014084507044, 500, 1500, "Goto Testing Project student list-0"], "isController": false}, {"data": [0.23987676056338028, 500, 1500, "Goto Testing Project student list-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 30313, 3626, 11.96186454656418, 11867.782040708646, 3, 67414, 34779.8, 46033.65000000004, 57239.0, 75.93094499747005, 233.15934458507004, 18.384315359966234], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "90th pct", "95th pct", "99th pct", "Throughput", "Received", "Sent"], "items": [{"data": ["Login as Teacher-1", 4381, 1042, 23.784524081259985, 11427.670394887004, 11, 51064, 23300.0, 34178.8, 50641.08, 11.13262588684922, 38.22612566640747, 1.9148767487637472], "isController": false}, {"data": ["Login as Teacher-0", 4381, 0, 0.0, 8895.416343300643, 23, 51162, 23733.0, 34258.0, 50935.420000000006, 10.987825860140351, 3.5396220724629686, 3.068865425781387], "isController": false}, {"data": ["Goto Testing activity", 4076, 831, 20.387634936211974, 12551.124631992154, 29, 67111, 32340.0, 38459.45, 45921.46, 10.364512478862853, 35.13756130567175, 2.378318869750677], "isController": false}, {"data": ["Goto Testing Project student list", 3838, 711, 18.525273579989577, 12809.886399166238, 36, 66986, 34420.0, 38583.0, 51067.61, 9.77177134360583, 56.96388488061533, 2.3494796254875703], "isController": false}, {"data": ["Goto Testing activity-0", 1201, 0, 0.0, 7187.404662781021, 17, 51253, 13738.0, 22496.699999999997, 51110.94, 3.063666093557883, 0.8796072573300954, 0.5505025011861822], "isController": false}, {"data": ["Visit website", 4582, 0, 0.0, 9255.533828022692, 3, 51176, 23151.7, 26426.799999999974, 46653.8, 11.491629569251991, 38.777161773465416, 1.7555371810144835], "isController": false}, {"data": ["Goto Testing activity-1", 1201, 0, 0.0, 9174.632805995008, 11, 50971, 22384.799999999996, 34159.9, 34436.92, 3.0657072185790537, 10.295866332708366, 0.5179368640763441], "isController": false}, {"data": ["Login as Teacher", 4381, 1042, 23.784524081259985, 20323.14220497605, 37, 67414, 47174.6, 56980.9, 66848.0, 10.976043613551067, 41.22429525394471, 4.953518363295519], "isController": false}, {"data": ["Goto Testing Project student list-0", 1136, 0, 0.0, 9388.100352112677, 17, 34659, 32431.3, 32799.949999999786, 34235.0, 2.9008304137769017, 0.8328556070804776, 0.552404229186031], "isController": false}, {"data": ["Goto Testing Project student list-1", 1136, 0, 0.0, 7596.544894366201, 10, 51044, 13822.2, 22225.6, 23867.0, 2.9006896798261628, 9.741671688400562, 0.49005792442375606], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Percentile 1
            case 8:
            // Percentile 2
            case 9:
            // Percentile 3
            case 10:
            // Throughput
            case 11:
            // Kbytes/s
            case 12:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["500", 3626, 100.0, 11.96186454656418], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 30313, 3626, "500", 3626, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Login as Teacher-1", 4381, 1042, "500", 1042, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": ["Goto Testing activity", 4076, 831, "500", 831, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Goto Testing Project student list", 3838, 711, "500", 711, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Login as Teacher", 4381, 1042, "500", 1042, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
