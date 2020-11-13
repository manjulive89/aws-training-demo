# aws-training-demo

The training task:

*	Create an AWS demo account
*	Prepare DynamoDB table that will contain books descriptions:
    ISBN:Title:Description
*	Create a Java web API app that will allow CRUD operations over this DynamoDB table
*	Deploy this app on EC2 instance
*	Setup SQS
*	Push change events from the application into the queue
*	Setup lambda that will read queue and update S3 bucket
*	Resources access should be controlled by IAM roles, e.g. DynamoDB table should be only accessible from application and S3 bucket only from lambda
*	All source code should be stored on the GitHub
*	All these services should be deployed via Github actions using AWS CLI and Cloudformation
