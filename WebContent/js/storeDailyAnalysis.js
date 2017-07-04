var table = document.getElementById("orderTable");
var dateSelector = document.getElementById("dateSelector");





//圓餅圖js部分
var content = [
          {
            "label": "牛肉麵",
            "value": 8,
            "color": "#2383c1"
          },
          {
            "label": "排骨飯!",
            "value": 5,
            "color": "#64a61f"
          },
          {
            "label": "餛飩湯",
            "value": 2,
            "color": "#7b6788"
          },
          {
            "label": "油飯",
            "value": 3,
            "color": "#a05c56"
          },
          {
            "label": "雞腿飯",
            "value": 2,
            "color": "#961919"
          },
          {
            "label": "麻油雞",
            "value": 1,
            "color": "#d8d239"
          },
          {
            "label": "其他",
            "value": 3,
            "color": "#e98125"
          }
        ];
    var pie = new d3pie("pieChart", {
      "header": {
        "title": {
          "text": "",
          "fontSize": 22,
          "font": "Microsoft JhengHei"
        },
        "subtitle": {
          "text": "",
          "color": "#999999",
          "fontSize": 10,
          "font": "verdana"
        },
        "titleSubtitlePadding": 12
      },
      "footer": {
        "text": "統計日期：2017/06/22",
        "color": "#999999",
        "fontSize": 14,
        "font": "Microsoft JhengHei",
        "location": "bottom-center"
      },
      "size": {
        "canvasHeight": 400,
        "canvasWidth": 500,
        "pieOuterRadius": "100%"
      },
      "data": {
        content
      },
      "labels": {
        "outer": {
          "format": "label-percentage1",
          "pieDistance": 24
        },
        "inner": {
          "format": "value"
        },
        "mainLabel": {
          "font": "verdana"
        },
        "percentage": {
          "color": "black",
          "font": "verdana",
          "decimalPlaces": 0
        },
        "value": {
          "color": "#e1e1e1",
          "font": "verdana"
        },
        "lines": {
          "enabled": true,
          "color": "#cccccc"
        },
        "truncation": {
          "enabled": true
        }
      },
      "effects": {
        "pullOutSegmentOnClick": {
          "effect": "linear",
          "speed": 400,
          "size": 10
        }
      },
      "callbacks": {}
    });