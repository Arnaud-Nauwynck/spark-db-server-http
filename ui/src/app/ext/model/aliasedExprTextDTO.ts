/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { BinaryOpExprDTO } from './binaryOpExprDTO';
import { FieldAccessExprDTO } from './fieldAccessExprDTO';
import { GroupAccumulatorExprDTO } from './groupAccumulatorExprDTO';
import { LiteralExprDTO } from './literalExprDTO';
import { RefByIdLookupExprDTO } from './refByIdLookupExprDTO';

export interface AliasedExprTextDTO { 
    expr?: BinaryOpExprDTO | FieldAccessExprDTO | GroupAccumulatorExprDTO | LiteralExprDTO | RefByIdLookupExprDTO;
    alias?: string;
}