<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/xml; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<auteurs>
	<s:iterator value="listeAuteurs">
		<auteur id="<s:property value="id" />"><s:property value="nom" />, <s:property value="prenom" /></auteur>
	</s:iterator>
</auteurs>
