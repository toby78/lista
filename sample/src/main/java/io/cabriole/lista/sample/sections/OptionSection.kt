/*
 * Copyright (c) 2020. Cabriole
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cabriole.lista.sample.sections

import android.view.View
import androidx.core.view.isVisible
import io.cabriole.lista.ListaSection
import io.cabriole.lista.ListaSectionViewHolder
import io.cabriole.lista.sample.R
import io.cabriole.lista.sample.databinding.SectionOptionBinding
import io.cabriole.lista.sample.model.OptionModel

class OptionSection(private val onOptionClickListener: OnOptionClickListener) :
    ListaSection<OptionModel>(R.layout.section_option) {

    interface OnOptionClickListener {
        fun onOptionClicked(optionModel: OptionModel)
    }

    override fun onCreateViewHolder(view: View): ListaSectionViewHolder<OptionModel> {
        return VH(view, onOptionClickListener)
    }

    override fun isForItem(item: Any): Boolean = item is OptionModel


    class VH(view: View, private val onOptionClickListener: OnOptionClickListener) :
        ListaSectionViewHolder<OptionModel>(view), View.OnClickListener {

        private val binding = SectionOptionBinding.bind(view)

        override fun onCreated() {
            super.onCreated()
            itemView.setOnClickListener(this)
        }

        override fun onBind(item: OptionModel) {
            super.onBind(item)
            binding.optionTitleTextView.setText(item.titleResource)
            if (item.subtitleResource == null) {
                binding.optionSubtitleTextView.text = ""
                binding.optionSubtitleTextView.isVisible = false
            } else {
                binding.optionSubtitleTextView.isVisible = true
                binding.optionSubtitleTextView.setText(item.subtitleResource)
            }
        }

        override fun onClick(v: View) {
            getItem()?.let { item -> onOptionClickListener.onOptionClicked(item) }
        }
    }

}
