/**
 * Copyright (C) 2010-2015 eBusiness Information, Excilys Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.davityle.ngprocessor.finders;

import com.github.davityle.ngprocessor.util.Option;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.inject.Inject;
import javax.tools.FileObject;
import javax.tools.StandardLocation;


public class FileHelper {

	private final ProcessingEnvironment processingEnv;

	@Inject
	public FileHelper(ProcessingEnvironment processingEnv) {
		this.processingEnv = processingEnv;
	}

	/**
	 * We use a dirty trick to find the AndroidManifest.xml file, since it's not
	 * available in the classpath. The idea is quite simple : create a fake
	 * class file, retrieve its URI, and start going up in parent folders to
	 * find the AndroidManifest.xml file. Any better solution will be
	 * appreciated.
	 */
	public Option<FileHolder> findRootProjectHolder() {
		Filer filer = processingEnv.getFiler();

		FileObject dummySourceFile;
		try {
			dummySourceFile = filer.createResource(StandardLocation.SOURCE_OUTPUT, "com", "dummy" + System.currentTimeMillis());
		} catch (IOException ignored) {
			return Option.absent();
		}
		String dummySourceFilePath = dummySourceFile.toUri().toString();

		if (dummySourceFilePath.startsWith("file:")) {
			if (!dummySourceFilePath.startsWith("file://")) {
				dummySourceFilePath = "file://" + dummySourceFilePath.substring("file:".length());
			}
		} else {
			dummySourceFilePath = "file://" + dummySourceFilePath;
		}

		URI cleanURI;
		try {
			cleanURI = new URI(dummySourceFilePath);
		} catch (URISyntaxException e) {
			return Option.absent();
		}
		try {
			File dummyFile = new File(cleanURI);
			File sourcesGenerationFolder = dummyFile.getParentFile();
			File projectRoot = sourcesGenerationFolder.getParentFile();

			return Option.of(new FileHolder(dummySourceFilePath, sourcesGenerationFolder, projectRoot));
		}catch(IllegalArgumentException ex){
			return Option.absent();
		}
	}

	static class FileHolder {
		String dummySourceFilePath;
		File sourcesGenerationFolder;
		File projectRoot;

		public FileHolder(String dummySourceFilePath, File sourcesGenerationFolder, File projectRoot) {
			this.dummySourceFilePath = dummySourceFilePath;
			this.sourcesGenerationFolder = sourcesGenerationFolder;
			this.projectRoot = projectRoot;
		}
	}

}
