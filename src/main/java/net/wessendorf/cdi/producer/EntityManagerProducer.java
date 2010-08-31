/*
 * Copyright (C) 2010 Bartosch Warzecha, Matthias Weßendorf.
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
package net.wessendorf.cdi.producer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import net.wessendorf.logger.JulFactory;

@Named("entityManagerProducer")
@RequestScoped
public class EntityManagerProducer
{
  @PersistenceUnit(unitName="hbase-addressbook")
  private EntityManagerFactory emf;
  
  @Produces @RequestScoped public EntityManager produceEntityManager()
  {
    EntityManager em = emf.createEntityManager();
    
    if (LOG.isLoggable(Level.INFO))
    {
      LOG.info(new StringBuilder().append("created EntityManager: ").append(emf).toString());
    }
    return em;
  }

  public void dispose(@Disposes EntityManager em)
  {
    if (em != null)
      em.close();
  }

  private static Logger LOG = JulFactory.createLogger(EntityManagerProducer.class);
}