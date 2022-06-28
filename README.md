# Concept
The main idea behind business control is to validate different types of data models with a set of logic encapsulated in beans.
In the process of performing business control, the following components are involved:

#### Business control
The entity performing the check - based on the result of the execution of the logic, a decision should be formed whether the call are valid (the answer is unsing for `Mismatch` building).
Should be marked with an annotation `BusinessControl` indicating the main attributes (code, execution method, etc.) and listing the metamodels. 
Also, the entity must be registered in the Spring Context as a bean.

#### Metamodel
Acts as a bridge between business control and the model being validated.
The metamodel describes a set of arguments that will be extracted from the described model and passed to business control for execution. 
The order in the arguments is important.
TODO to add DSL.

#### Settings
Used to control the processing of business controls, f.e. a message is used in mismatch creating, an activity is used to determine whether a business control should be invoked, etc.
It is also possible to specify additional settings and use them in filters.

#### Params
Similar to settings, but are not used in the process of calling the business control, they are passed to the executed business control to manipulate with logic.
A typical example is an admin setting for business control, such as comparison date or other constant.

#### Filters
Filters determine whether business control should be run based on the input request and business control settings.
By default, there is one `ActiveSettingsBusinessControlFilterProcessor` filter that checks for business control activity and skips in invocation it out if it is non-active.

It is possible to add custom filters by implementing `BusinessControlFilterProcessor` for different situations, such as business controls being active for certain requests or fields, etc.

#### Mismatch
Its a report item that represent failed business control. 
Contains the business control code, its criticality and message. 
The error message can use templating (the default is [handlebarsjs](https://handlebarsjs.com/) unless you implement `BusinessControlMismatchMessageFormatter`).
The fields that led to the business control failure are also might be indicated.

# Configuration
`app.blocks.business.control.mismatch.builder.mode` defines the algorithm for adding fields to the Mismatch.
Can be one of `[ALL, SPECIFIED, MIXED, NONE]`:
* `ALL` - all fields that were passed to business control as arguments will be added to mismatch  
* `SPECIFIED` - only fields that were mentioned in `ReportEntity.mismatchArgNames` during business control will be added to mismatch.  
* `MIXED` - uses `SPECIFIED` algorithm if fields were provided from business control, otherwise `ALL` for each business control invocation.
* `NONE` - fields adding to the mismatch will be skipped.

# Quick start
TODO

# How to extend
TODO

#Flow

![](business-control-flow.svg)

# TODO
* Create demo project
* Add DSL for `ArgMetamodel.path`
* Add cache for each ArgMetamodel.path
* Add runner
* Check "distance" and pick metamodel by closest superclass here `BusinessControlArgMetamodelService.java:44` 
