package com.marklogic.hub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marklogic.hub.DataHub;
import com.marklogic.hub.ServerValidationException;
import com.marklogic.hub.config.EnvironmentConfiguration;
import com.marklogic.hub.exception.DataHubException;
import com.marklogic.hub.web.controller.MainPageController;

@Service
public class DataHubService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);
	
	@Autowired
	private EnvironmentConfiguration environmentConfiguration;
	
	public void install() throws DataHubException {
		DataHub dataHub = getDataHub();
		try {
			dataHub.install();
		} catch(Throwable e) {
			throw new DataHubException(e.getMessage(), e);
		}
	}
	
	private DataHub getDataHub() throws DataHubException {
		try {
			LOGGER.info("Host is "+environmentConfiguration.getMLHost());
			LOGGER.info("username is "+environmentConfiguration.getMLUsername());
			LOGGER.info("password is "+environmentConfiguration.getMLPassword());
			return new DataHub(environmentConfiguration.getMLHost(), environmentConfiguration.getMLUsername(), 
					environmentConfiguration.getMLPassword());
		} catch(Throwable e) {
			throw new DataHubException(e.getMessage(), e);
		}
	}
	
	public boolean isInstalled() throws DataHubException {
		DataHub dataHub = getDataHub();
		try {
			return dataHub.isInstalled();
		} catch(Throwable e) {
			throw new DataHubException(e.getMessage(), e);
		}
	}
	
	public boolean isServerAcceptable() throws DataHubException {
		DataHub dataHub = getDataHub();
		try {
			dataHub.validateServer();
			return true;
		} catch(ServerValidationException exception) {
			return false;
		} catch(Throwable e) {
			throw new DataHubException(e.getMessage(), e);
		}
	}
	
	public void uninstall() throws DataHubException {
		DataHub dataHub = getDataHub();
		try {
			dataHub.uninstall();
		} catch(Throwable e) {
			throw new DataHubException(e.getMessage(), e);
		}
    }

}