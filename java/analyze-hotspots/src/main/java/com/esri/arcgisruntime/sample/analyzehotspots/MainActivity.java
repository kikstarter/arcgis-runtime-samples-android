/* Copyright 2017 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.esri.arcgisruntime.sample.analyzehotspots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements DateRangeDialogFragment.OnAnalyzeButtonClickListener,
    ProgressDialogFragment.OnProgressDialogCancelListener {

  private final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragmentContainer, findAnalyzeHotspotsFragment(), AnalyzeHotspotsFragment.class.getSimpleName())
          .commit();
    }
  }

  @Override public void onAnalyzeButtonClick(String fromDate, String toDate) {
    findAnalyzeHotspotsFragment().analyzeHotspots(fromDate, toDate);
  }

  @Override public void onProgressDialogCancel() {
    findAnalyzeHotspotsFragment().cancelGeoProcessingJob();
  }

  private AnalyzeHotspotsFragment findAnalyzeHotspotsFragment() {
    AnalyzeHotspotsFragment fragment = (AnalyzeHotspotsFragment) getSupportFragmentManager()
        .findFragmentByTag(AnalyzeHotspotsFragment.class.getSimpleName());
    if (fragment != null) {
      return fragment;
    } else {
      return AnalyzeHotspotsFragment.newInstance();
    }
  }
}