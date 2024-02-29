/*******************************************************************************
* Copyright (c) 2019 Red Hat Inc. and others.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License v. 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
* which is available at https://www.apache.org/licenses/LICENSE-2.0.
*
* SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package org.eclipse.lsp4jdt.core.utils;

import java.util.logging.Logger;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.lsp4jdt.commons.ClasspathKind;

/**
 * LSP4JDT utilities.
 *
 * @author Angelo ZERR
 *
 */
public class LSP4JDTUtils {

    private static final Logger LOGGER = Logger.getLogger(LSP4JDTUtils.class.getName());

    private LSP4JDTUtils() {

    }

    /**
     * Returns the project URI of the given project.
     *
     * @param project the java project
     * @return the project URI of the given project.
     */
    public static String getProjectURI(IJavaProject project) {
        return getProjectURI(project.getProject());
    }

    /**
     * returns the project URI of the given project.
     *
     * @param project the project
     * @return the project URI of the given project.
     */
    public static String getProjectURI(IProject project) {
        return project.getLocation().toOSString();
    }

    /**
     * Returns true if the given resource <code>resource</code> is on the 'test'
     * classpath of the given java project <code>javaProject</code> and false
     * otherwise.
     *
     * @param resource the resource
     * @param javaProject the project.
     * @return true if the given resource <code>resource</code> is on the 'test'
     *         classpath of the given java project <code>javaProject</code> and
     *         false otherwise.
     */
    public static ClasspathKind getClasspathKind(IResource resource, IJavaProject javaProject) {
        IPath exactPath = resource.getFullPath();
        IPath path = exactPath;

        // ensure that folders are only excluded if all of their children are excluded
        int resourceType = resource.getType();
        boolean isFolderPath = resourceType == IResource.FOLDER || resourceType == IResource.PROJECT;

        IClasspathEntry[] classpath;
        try {
            classpath = ((JavaProject) javaProject).getResolvedClasspath();
        } catch (JavaModelException e) {
            return ClasspathKind.NONE; // not a Java project
        }
        for (int i = 0; i < classpath.length; i++) {
            IClasspathEntry entry = classpath[i];
            IPath entryPath = entry.getPath();
            if (entryPath.equals(exactPath)) { // package fragment roots must match exactly entry pathes (no exclusion
                                               // there)
                return getClasspathKind(entry);
            }
            // https://bugs.eclipse.org/bugs/show_bug.cgi?id=276373
            // When a classpath entry is absolute, convert the resource's relative path to a
            // file system path and compare
            // e.g - /P/lib/variableLib.jar and /home/P/lib/variableLib.jar when compared
            // should return true
            if (entryPath.isAbsolute()
                && entryPath.equals(ResourcesPlugin.getWorkspace().getRoot().getLocation().append(exactPath))) {
                return getClasspathKind(entry);
            }
            if (entryPath.isPrefixOf(path)) {
                // && !Util.isExcluded(path, ((ClasspathEntry)
                // entry).fullInclusionPatternChars(),
                // ((ClasspathEntry) entry).fullExclusionPatternChars(), isFolderPath)) {
                return getClasspathKind(entry);
            }
        }
        return ClasspathKind.NONE;

    }

    /**
     * Returns true if the given <code>project</code> has a nature specified by
     * <code>natureId</code> and false otherwise.
     *
     * @param project the project
     * @param natureId the nature id
     * @return true if the given <code>project</code> has a nature specified by
     *         <code>natureId</code> and false otherwise.
     */
    public static boolean hasNature(IProject project, String natureId) {
        try {
            return project != null && project.hasNature(natureId);
        } catch (CoreException e) {
            return false;
        }
    }

    private static ClasspathKind getClasspathKind(IClasspathEntry entry) {
        return entry.isTest() ? ClasspathKind.TEST : ClasspathKind.SRC;
    }

    /**
     * Returns an array of all the java projects that are currently loaded into the JDT
     * workspace.
     *
     * @return an array of all the projects that are currently loaded into the JDT
     *         workspace
     */
    public static IJavaProject[] getJavaProjects() {
        return Stream.of(getAllProjects()) //
                        .filter(LSP4JDTUtils::isJavaProject) //
                        .map(p -> JavaCore.create(p)) //
                        .filter(p -> p != null) //
                        .toArray(IJavaProject[]::new);
    }

    /**
     * Returns an array of all the projects that are currently loaded into the JDT
     * workspace.
     *
     * @return an array of all the projects that are currently loaded into the JDT
     *         workspace
     */
    private static IProject[] getAllProjects() {
        return ResourcesPlugin.getWorkspace().getRoot().getProjects();
    }

    private static boolean isJavaProject(IProject project) {
        if (project == null) {
            return false;
        }
        try {
            return project.hasNature(JavaCore.NATURE_ID);
        } catch (CoreException e) {
            return false;
        }
    }
}