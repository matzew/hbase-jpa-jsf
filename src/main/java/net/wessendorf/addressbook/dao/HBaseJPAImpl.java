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
package net.wessendorf.addressbook.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.wessendorf.addressbook.Contact;
import net.wessendorf.cdi.transactional.Transactional;
import net.wessendorf.logger.JulFactory;

public class HBaseJPAImpl implements ContactDao
{
  @Inject protected EntityManager em;

  @Transactional
  public void save(Contact contact)
  {
    if (LOG.isLoggable(Level.INFO))
    {
      LOG.info("persisting " + contact);
    }

    em.persist(contact);
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<Contact> findAllContacts()
  {
    Query query = em.createNamedQuery("findAll");
    List<Contact> results = (List<Contact>) query.getResultList();
    return results;
  }
  
  private static Logger LOG = JulFactory.createLogger(HBaseJPAImpl.class);
}