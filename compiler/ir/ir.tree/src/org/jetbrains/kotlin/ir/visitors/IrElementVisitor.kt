/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.ir.visitors

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*

interface IrElementVisitor<out R, in D> {
    fun visitElement(element: IrElement, data: D): R
    fun visitModule(declaration: IrModule, data: D): R = visitElement(declaration, data)
    fun visitFile(declaration: IrFile, data: D): R = visitElement(declaration, data)

    fun visitDeclaration(declaration: IrDeclaration, data: D): R = visitElement(declaration, data)
    fun visitClass(declaration: IrClass, data: D): R = visitDeclaration(declaration, data)
    fun visitFunction(declaration: IrFunction, data: D): R = visitDeclaration(declaration, data)
    fun visitPropertyGetter(declaration: IrPropertyGetter, data: D): R = visitFunction(declaration, data)
    fun visitPropertySetter(declaration: IrPropertySetter, data: D): R = visitFunction(declaration, data)
    fun visitProperty(declaration: IrProperty, data: D): R = visitDeclaration(declaration, data)
    fun visitSimpleProperty(declaration: IrSimpleProperty, data: D): R = visitProperty(declaration, data)
    fun visitDelegatedProperty(declaration: IrDelegatedProperty, data: D): R = visitProperty(declaration, data)
    fun visitVariable(declaration: IrVariable, data: D) = visitDeclaration(declaration, data)

    fun visitBody(body: IrBody, data: D): R = visitElement(body, data)
    fun visitExpressionBody(body: IrExpressionBody, data: D): R = visitBody(body, data)

    fun visitExpression(expression: IrExpression, data: D): R = visitElement(expression, data)
    fun <T> visitConst(expression: IrConst<T>, data: D): R = visitExpression(expression, data)
    fun visitReturn(expression: IrReturn, data: D): R = visitExpression(expression, data)
    fun visitBlock(expression: IrBlock, data: D): R = visitExpression(expression, data)
    fun visitStringConcatenation(expression: IrStringConcatenation, data: D) = visitExpression(expression, data)
    fun visitThisReference(expression: IrThisReference, data: D) = visitExpression(expression, data)

    fun visitDeclarationReference(expression: IrDeclarationReference, data: D) = visitExpression(expression, data)
    fun visitSingletonReference(expression: IrGetSingletonValue, data: D) = visitDeclarationReference(expression, data)
    fun visitGetObjectValue(expression: IrGetObjectValue, data: D) = visitSingletonReference(expression, data)
    fun visitGetEnumValue(expression: IrGetEnumValue, data: D) = visitSingletonReference(expression, data)
    fun visitGetVariable(expression: IrGetVariable, data: D) = visitDeclarationReference(expression, data)
    fun visitSetVariable(expression: IrSetVariable, data: D) = visitDeclarationReference(expression, data)
    fun visitGetExtensionReceiver(expression: IrGetExtensionReceiver, data: D) = visitDeclarationReference(expression, data)
    fun visitCall(expression: IrCall, data: D) = visitDeclarationReference(expression, data)

    fun visitTypeOperator(expression: IrTypeOperatorCall, data: D) = visitExpression(expression, data)

    fun visitWhen(expression: IrWhen, data: D) = visitExpression(expression, data)
    fun visitLoop(loop: IrLoop, data: D) = visitExpression(loop, data)
    fun visitWhileLoop(loop: IrWhileLoop, data: D) = visitLoop(loop, data)
    fun visitDoWhileLoop(loop: IrDoWhileLoop, data: D) = visitLoop(loop, data)

    // NB Use it only for testing purposes; will be removed as soon as all Kotlin expression types are covered
    fun visitDummyDeclaration(declaration: IrDummyDeclaration, data: D) = visitDeclaration(declaration, data)
    fun visitDummyExpression(expression: IrDummyExpression, data: D) = visitExpression(expression, data)

}