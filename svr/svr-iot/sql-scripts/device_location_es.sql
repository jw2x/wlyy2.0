
-- 设备地址elasticsearch 新增mapping
curl -XPUT http://url/device_location_index
{
        "mappings": {
            "device_location_type": {
                "properties": {
                    "categoryCode": {
                        "type": "string"
                    },
                    "createTime": {
                        "type": "date",
                        "format": "yyyy-MM-dd HH:mm:ss"
                    },
                    "deviceSn": {
                        "type": "string"
                    },
                    "deviceTime": {
                        "type": "date",
                        "format": "yyyy-MM-dd HH:mm:ss"
                    },
                    "idCard": {
                        "type": "string"
                    },
                    "location": {
                        "type": "geo_point"
                    }
                }
            }
        }
}


