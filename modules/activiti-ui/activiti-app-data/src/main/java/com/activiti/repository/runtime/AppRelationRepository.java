/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.activiti.repository.runtime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.activiti.domain.runtime.AppRelation;

/**
 * AppRelation is shared between editor-rest (used during publish) and runtime (runtime app deployment)
 * 
 * @author jbarrez
 */
public interface AppRelationRepository extends JpaRepository<AppRelation, Long> {
	
	List<AppRelation> findByRuntimeAppIdAndType(Long runtimeAppId, String type);
	
	@Query("select a from AppRelation a inner join a.modelHistory m where m.modelId = :runtimeModelId")
	List<AppRelation> findByRuntimeModelId(@Param("runtimeModelId") Long runtimeModelId);
	
	@Modifying
	@Query("delete from AppRelation ar where ar.runtimeAppId = :runtimeAppId")
	void deleteAppRelationsForRuntimeAppDeployment(@Param("runtimeAppId") Long runtimeAppId);

}
