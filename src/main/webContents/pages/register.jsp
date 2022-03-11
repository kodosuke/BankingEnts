<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>


<h2> Register </h2>

<s:form action="registerUser">

<s:textfield name="customerName" label="Enter your customer name " required="true"/>
<s:textfield name="password" label="Create your account password" required="true"/>
<s:select list="{'SAVINGS', 'CURRENT'}" name="accountType" label="Choose your account type" required="true"/>

<s:submit value="Submit"/>
</s:form>