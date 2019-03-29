package com.smile.clz.api.webapp.controller;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.ZoneManager;
import com.smile.clz.api.beans.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@RestController
@CrossOrigin(maxAge = 3600)
public class ZoneController extends AbstractRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZoneController.class);

    @Autowired
    private ZoneManager zoneManager;

    @RequestMapping(value = "zones/zoneName/{zoneName}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public Zone getZoneByZoneName(@PathVariable String zoneName) throws ClassApiException {
        LOGGER.info("Request received to get zone by zone name [{}]", zoneName);
        Zone zoneObj;
        zoneObj = zoneManager.getZoneByZoneName(zoneName);

        LOGGER.info("Return response for get zone by zone name");
        return zoneObj;
    }

    @RequestMapping(value = "zones",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Zone> getAllZones() throws ClassApiException {
        LOGGER.info("Request received to get all zones");
        List<Zone> listOfZones = null;
        listOfZones = zoneManager.getAllZones();

        LOGGER.info("Return response for get all zones");
        return listOfZones;
    }
}
