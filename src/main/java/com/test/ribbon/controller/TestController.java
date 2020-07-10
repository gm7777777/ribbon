package com.test.ribbon.controller;

import com.test.ribbon.service.GetURLService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    GetURLService getURLService;

    @ApiOperation(value = "loadbalance interface to get tiny url based on /test address", notes = "", response = String.class, tags={ "convertURL", })

    //这里是显示你可能返回的http状态，以及原因。比如404 not found, 303 see other
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input", response = String.class) })

    /**
     * jsonStr 包括 id,urltr,comment,
     */
    @RequestMapping(value="/test",method= RequestMethod.POST)
    public String convertURL(@RequestBody @ApiParam(value = "json paramter inlcude id ,urlStr , comment ,shortenURL", required = true)   String jsonStr) {

        return getURLService.getShortenURL(jsonStr);
    }
}
