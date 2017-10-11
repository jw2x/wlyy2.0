package com.yihu.jw.manage.service.doctorManage;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
/*
@Service
public class DoctorService {

    @Value("${spring.gateway}"+ DoctorContants.api_common)
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserService userService;

   public Envelop list(Integer size, Integer page,Map<String,String> map) {
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> filters = new HashMap<>();
        req.put("size", size);
        req.put("page", page);
        req.put("sorts", map.get("sorts"));
        String name = map.get("name");
        String saasId = map.get("saasId");
        if (StringUtils.isNotBlank(name)) {
            filters.put("name", name);
        }
        if (StringUtils.isNotBlank(saasId)) {
            filters.put("saasId", saasId);
        }
        req.put("filters",filters);

        return template.getForObject(url + DoctorContants.Doctor.api_getList+"?size={size}&page={page}&sorts={sorts}&filters={filters}", Envelop.class,req);

    }

    public Envelop deleteByCode(String codes,String userCode) {
        //delete 没有返回值....
        //template.delete(url + "/wechatConfig/delete?codes={codes}", codes);
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        Map<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("userName", userName);
        String urlRequest = url +"wechatConfig/"+codes+"?userCode={userCode}&userName={userName}";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(urlRequest,multiValueMap);
        Envelop envelop = restTemplateUtil.exchange(urlRequest, HttpMethod.DELETE, Envelop.class,map);
        return envelop;
    }

    public Envelop findByCode(String code) {
        Envelop envelop = template.getForObject(url +"wechatConfig/"+code, Envelop.class);
        return envelop;
    }

    public Envelop saveOrUpdate(WechatConfig wechatConfig,String userCode) throws JsonProcessingException {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();

        //设置值
        if(wechatConfig.getId()==null){
            wechatConfig.setCreateUser(userCode);
            wechatConfig.setCreateUserName(userName);
        }
        wechatConfig.setUpdateUserName(userName);
        wechatConfig.setUpdateUser(userCode);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonObj = JSONObject.fromObject(wechatConfig);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        Envelop envelop =null;
        if(wechatConfig.getId()==null){//说明是保存
            ResponseEntity<Envelop> responseEntity = template.postForEntity(url + WechatContants.WxConfig.api_create, formEntity, Envelop.class);
            envelop = responseEntity.getBody();
            return envelop;
        }
        ResponseEntity<Envelop> resp = template.exchange(url + WechatContants.WxConfig.api_update,HttpMethod.PUT,formEntity,Envelop.class);
        envelop = resp.getBody();
        return envelop;
    }

    public Envelop getListNoPage(String fields, Map<String, Object> filter, String sorts) {
        Map<String, Object> map = new HashMap<>();
        map.put("fields",fields);
        map.put("sorts",sorts);
        map.put("filter",filter);
        Envelop forObject = template.getForObject(url + WechatContants.WxConfig.api_getWechatNoPage+"?fields={fields}&filter={filter}&sorts={sorts}", Envelop.class,map);
        return forObject;
    }
}
*/
